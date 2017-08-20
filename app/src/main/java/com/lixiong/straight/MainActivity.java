package com.lixiong.straight;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lixiong.straight.base.BaseFragment;
import com.lixiong.straight.base.BaseFragmentMainActivity;
import com.lixiong.straight.common.utils.AppManger;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.interfaces.ICheckedChanged;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.message.fragment.MessageFragment;
import com.lixiong.straight.my.fragment.MyFragment;
import com.lixiong.straight.my.viewholder.MyHeadHolder;
import com.lixiong.straight.project.fragment.ProjectFragment;
import com.lixiong.straight.project.search.SearchHolder;
import com.lixiong.straight.service.LocalService;
import com.lixiong.straight.service.LocationService;
import com.lixiong.straight.service.RemoteService;
import com.lixiong.straight.view.TopBar;
import com.zaaach.citypicker.CityPickerActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import okhttp3.Call;

public class MainActivity extends BaseFragmentMainActivity {

    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.rb_project)
    RadioButton rbProject;
    @Nullable
    @Bind(R.id.fl_project_top)
    FrameLayout flProjectTop;
    @Nullable
    @Bind(R.id.message_top)
    TopBar messageTop;
    @Nullable
    @Bind(R.id.fl_my_head)
    FrameLayout flMyHead;
    //是否为退出登录跳转过来的,需进入我的页面
    private boolean isEnterMy;
    //状态码
    private String statusCode;
    private SharedPreferenceUtil sharedPreferenceUtil;
    private ProjectFragment projectFragment;
    private SearchHolder searchHolder;
    private String latLongString;
    private LoadingDialog loadingDialog;
    private LocationService mLocationService;
    private String city;
    //定义一个全局变量用来标记
    private boolean isConnected = false;


    @Override
    protected int initFrameLayoutId() {
        return R.id.fl_main;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        LogUtil.i("主页面获取的Bundle的值:" + bundle);
        if (bundle != null) {
            isEnterMy = bundle.getBoolean(Config.EXIT_LOGIN, false);
        }
        projectFragment = new ProjectFragment();
        sharedPreferenceUtil = new SharedPreferenceUtil(this, Config.SP);
        statusCode = sharedPreferenceUtil.get(Config.STATUS_CODE_KEY, "");
        city = sharedPreferenceUtil.get(Config.CURRENT_CITY,"");
        LogUtil.e("主页面接收到的状态码为：" + statusCode);
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
    }

    @Override
    protected BaseFragment[] initClass() {
        return new BaseFragment[]{projectFragment, new MessageFragment(), new MyFragment()};
    }

    /*private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Config.LOCATION_CITY_WHAT){
                double[] data = (double[]) msg.obj;
                List<Address> addList = null;
                Geocoder ge = new Geocoder(MainActivity.this);
                try {
                    addList = ge.getFromLocation(data[0], data[1], 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (addList != null && addList.size() > 0) {
                    for (int i = 0; i < addList.size(); i++) {
                        Address ad = addList.get(i);
                        latLongString = ad.getLocality();
                    }
                }
                if (!TextUtils.isEmpty(latLongString)) {
                    if (latLongString.contains("市")) {
                        latLongString = latLongString.replace("市", "");
                    }
                    sharedPreferenceUtil.set(Config.CURRENT_CITY, latLongString);
                    searchHolder.getTvCity().setText(latLongString);
                }
            }
        }
    };*/

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            UIUtils.toast("未成功连接定位服务");
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UIUtils.toast("成功连接定位服务");
            mLocationService = ((LocationService.LocationBinder) service).getService();
            mLocationService.setOnGetLocationListener(new LocationService.OnGetLocationListener() {
                @Override
                public void getLocation(String lastLatitude, String lastLongitude, String latitude, String longitude, String country, String locality, String street) {
                    if(!TextUtils.isEmpty(locality))sharedPreferenceUtil.set(Config.CURRENT_CITY, locality);
                    LogUtil.i("定位城市："+locality);
//                    searchHolder.getTvCity().setText(locality);
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        //预先判断ServiceConnection 是否为空，不空再解绑服务。
        if(isConnected){
            unbindService(conn);//conn表示ServiceConnection 对象
            isConnected = false;
        }
        super.onDestroy();
    }

    @Override
    protected void setListener() {
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener(new ICheckedChanged() {
            @Override
            public void checkedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_project:
                        position = 0;
                        ViewUtils.showView(flProjectTop);
                        ViewUtils.goneView(messageTop,flMyHead);
                        searchHolder = new SearchHolder(MainActivity.this);
                        if(TextUtils.isEmpty(city)){
//                            searchHolder.setHandler(handler);
//                            在5.0的手机上覆写onDestory()方法时调用unbindService(conn)并没有出现这个问题，
//                            但是在4.4的机子上跑的时候，退出发现就报上述错误了。可能有人会想，明明有使用bindService绑定服务呀，
//                            而且查看进程服务也在运行。没错，我一开始就是这么认为的，然后我点进bindService这个方法去看源码，
//                            发现它返回类型不是void,而是boolean(作者比较粗心，学习的时候没注意)
                            isConnected  = bindService(new Intent(MainActivity.this, LocationService.class), conn, Context.BIND_AUTO_CREATE);
                        }else {
                            searchHolder.getTvCity().setText(city);
                        }
                        flProjectTop.addView(searchHolder.getView());
                        if (Config.PERSON_SINGLE_ENTERPRISE.equals(statusCode) ||
                                Config.PERSON_SINGLE_INDIVIDUAL.equals(statusCode)) {
                            rbProject.setText("服务商");
                        } else if (Config.ORDERS_SINGLE_ENTERPRISE.equals(statusCode) ||
                                Config.ORDERS_SINGLE_INDIVIDUAL.equals(statusCode)) {
                            rbProject.setText("项目");
                        }
                        break;

                    case R.id.rb_message:
                        position = 1;
                        ViewUtils.showView(messageTop);
                        ViewUtils.goneView(flProjectTop,flMyHead);
                        break;

                    case R.id.rb_my:
                        position = 2;
                        ViewUtils.showView(flMyHead);
                        ViewUtils.goneView(messageTop,flProjectTop);
                        MyHeadHolder myHeadHolder = new MyHeadHolder(MainActivity.this);
                        flMyHead.addView(myHeadHolder.getView());
                        break;
                }
            }
        }));
        if (isEnterMy) {
            rgMain.check(R.id.rb_my);
        } else {
            rgMain.check(R.id.rb_project);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Config.REQUEST_CODE_PICK_CITY && resultCode == RESULT_OK) {
            if (data != null) {
                String city = data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                LogUtil.i("当前选择：" + city);
                searchHolder.getTvCity().setText(city);
                sharedPreferenceUtil.set(Config.CURRENT_CITY, city);
                loadingDialog = new LoadingDialog(this);
                OkHttpUtils.get().url(URLParam.QUERY_BY_CONDITION).addParams("city",city+"市").build()
                        .execute(new CategoryStringCallback());
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppManger.getAppManager().appExit(this);
    }

    private class CategoryStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.i("查询类别数据失败 "+e);
            loadingDialog.dismiss();
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.i("查询类别数据成功 response"+response);
            loadingDialog.dismiss();
            projectFragment.upDataUI(response);
        }
    }
}

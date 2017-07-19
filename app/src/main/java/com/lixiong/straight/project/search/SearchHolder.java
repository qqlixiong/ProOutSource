package com.lixiong.straight.project.search;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.zaaach.citypicker.CityPickerActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissonItem;

/**
 * Created by john on 2017/5/20.
 */

public class SearchHolder extends BaseHolder {
    @Bind(R.id.tv_city)
    TextView tvCity;
    private LocationManager locationManager;
    private Context context;

    private double latitude = 0;
    private double longitude = 0;

    public SearchHolder(final Context context) {
        this.context = context;
    }

    public void setHandler(final Handler handler){
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        new Thread() {
            @Override
            public void run() {
                List<PermissonItem> permissonItems = new ArrayList<PermissonItem>();
                permissonItems.add(new PermissonItem(Manifest.permission.ACCESS_FINE_LOCATION, "Location", R.drawable.permission_ic_location));
                HiPermission.create(context).permissions(permissonItems)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {
                                LogUtil.i("onClose");
                            }

                            @Override
                            public void onFinish() {
                                LogUtil.i("onFinish");
                            }

                            @Override
                            public void onDeny(String permisson, int position) {
                                LogUtil.i("onDeny");
                            }

                            @Override
                            public void onGuarantee(String permisson, int position) {
                                LogUtil.i("onGuarantee");
                            }
                        });
                Location location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude(); // 经度
                    longitude = location.getLongitude(); // 纬度
                    double[] data = {latitude, longitude};
                    Message msg = handler.obtainMessage();
                    msg.what = Config.LOCATION_CITY_WHAT;
                    msg.obj = data;
                    handler.sendMessage(msg);
                }
            }
        }.start();
    }

    public TextView getTvCity() {
        return tvCity;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.pro_search_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick({R.id.rl_select_city,R.id.rl_search})
    public void onViewClicked(View view) {
        LogUtil.i("context:"+context);
        switch (view.getId()){
            case R.id.rl_select_city:
                Intent intent = new Intent(context, CityPickerActivity.class);
                if(context instanceof Activity){
                    ((Activity)context).startActivityForResult(intent,
                            Config.REQUEST_CODE_PICK_CITY);
                }
                break;

            case R.id.rl_search:
                IntentUtil.startActivity(context,SearchActivity.class);
                break;
        }
    }
}

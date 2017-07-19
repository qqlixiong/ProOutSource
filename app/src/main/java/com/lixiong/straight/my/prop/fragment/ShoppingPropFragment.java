package com.lixiong.straight.my.prop.fragment;


import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseMyFragment;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.my.bean.PersonBean;
import com.lixiong.straight.my.prop.viewholder.ShoppingPropHolder;
import com.lixiong.straight.my.prop.bean.ShoppingProp;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * 道具商城
 */
public class ShoppingPropFragment extends BaseMyFragment {

    @Bind(R.id.rv_shopping_prop)
    RecyclerView rvShoppingProp;
    @Bind(R.id.fl_shopping_prop)
    FrameLayout flShoppingProp;
//    private List<ShoppingProp.XmzbToolsEntityCustomBean> list;
    private LoadingDialog loadingDialog;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loadingDialog = new LoadingDialog(mContext);
            if(msg.what==Config.SHOPPING_PROP_WHAT){
                String identityTypeId = "1004";
//                List<PersonBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBeanList =
//                        PersonBean.XmzbAccountEntityCustomBean.listAll(PersonBean.XmzbAccountEntityCustomBean.class);
                List<PersonBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBeanList = Constant.getUserMessage();
                if(xmzbAccountEntityCustomBeanList.size()>0){
                    PersonBean.XmzbAccountEntityCustomBean personMsg = xmzbAccountEntityCustomBeanList.get(0);
                    identityTypeId = personMsg.getIdentityTypeid();
                }
                OkHttpUtils.get().url(URLParam.SHOPPING_PROP_URL)
                        .addParams("identityTypeid",identityTypeId)
                        .build().execute(new PropStringCallback());
            }
        }
    };

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_shopping_prop;
    }

    @Override
    protected void initData() {
        super.initData();
//        list = ShoppingProp.XmzbToolsEntityCustomBean.listAll(ShoppingProp.XmzbToolsEntityCustomBean.class);
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
//        if (list.size() > 0) {
//            loadShoppingProp(list);
//        }
    }

    @Override
    protected void lazyLoad() {
//        if(list == null){
//            list = ShoppingProp.XmzbToolsEntityCustomBean.listAll(ShoppingProp.XmzbToolsEntityCustomBean.class);
//        }
//        if(list.size()==0){
            handler.sendEmptyMessage(Config.SHOPPING_PROP_WHAT);
//        }
    }

    private class PropStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.i("获取道具商城信息失败：" + e);
//            UIUtils.toast("获取数据失败");
            loadingDialog.dismiss();

            loadShoppingProp(Constant.getShoppingProp());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.i("获取道具商城信息成功：" + response);
            loadingDialog.dismiss();
            if (!TextUtils.isEmpty(response)) {
                ShoppingProp shoppingProp = JSON.parseObject(response, ShoppingProp.class);
                List<ShoppingProp.XmzbToolsEntityCustomBean> xmzbToolsEntityCustomBeenList =
                        shoppingProp.getXmzbToolsEntityCustom();
                int size = xmzbToolsEntityCustomBeenList.size();
                if (size > 0) {
                    List<ShoppingProp.XmzbToolsEntityCustomBean> shoppingProp2 =
                            ShoppingProp.XmzbToolsEntityCustomBean.listAll(ShoppingProp.XmzbToolsEntityCustomBean.class);
                    if(shoppingProp2.size()==0){
                        for (ShoppingProp.XmzbToolsEntityCustomBean xmzbToolsEntityCustomBean : xmzbToolsEntityCustomBeenList) {
                            xmzbToolsEntityCustomBean.save();
                        }
                    }
                    loadShoppingProp(xmzbToolsEntityCustomBeenList);
                }else {
                    UIUtils.toast("获取数据失败");
                }
            }else {
                UIUtils.toast("获取数据失败");
            }
        }
    }

    private void loadShoppingProp(List<ShoppingProp.XmzbToolsEntityCustomBean> xmzbToolsEntityCustomBeenList) {
        recyclerViewUtil.initRecyclerView(rvShoppingProp, xmzbToolsEntityCustomBeenList,
                new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false), ShoppingPropHolder.class);
    }
}

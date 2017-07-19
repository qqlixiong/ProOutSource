package com.lixiong.straight.my.viewholder;

import android.content.Context;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.PublicTools;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.collect.MyCollectActivity;
import com.lixiong.straight.my.follow.MyFollowActivity;
import com.lixiong.straight.my.identification.PersonalIdentificationActivity;
import com.lixiong.straight.my.prop.activity.MyPropActivity;
import com.lixiong.straight.my.prop.activity.ShoppingPropActivity;

import butterknife.OnClick;

/**
 * Created by john on 2017/5/20.
 */

public class MyHolder extends BaseHolder {
    private Context context;

    public MyHolder(Context context) {
        this.context = context;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.my_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick({R.id.mhv_my_prop, R.id.mhv_shopping_prop, R.id.mhv_my_collect, R.id.mhv_my_follow, R.id.mhv_enterprise_certification})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mhv_my_prop:
                IntentUtil.startActivity(context, MyPropActivity.class);
                break;
            case R.id.mhv_shopping_prop:
                IntentUtil.startActivity(context, ShoppingPropActivity.class);
                break;
            case R.id.mhv_my_collect:
                IntentUtil.startActivity(context, MyCollectActivity.class);
                break;
            case R.id.mhv_my_follow:
                IntentUtil.startActivity(context, MyFollowActivity.class);
                break;
            case R.id.mhv_enterprise_certification:
                if(PublicTools.isToLogin()){
                    PublicTools.toLogin(context);
                }else {
                    IntentUtil.startActivity(context,PersonalIdentificationActivity.class);
                }
                break;
        }
    }
}

package com.lixiong.straight.login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.TransparentStatusBar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 选择身份
 */
public class IdentitySelectActivity extends AppCompatActivity implements IActivity ,TransparentStatusBar {

    @Bind(R.id.tv_register_phone)
    TextView tvRegisterPhone;
    private String userName;
    private String password;

    @Override
    public int getLayoutId() {
        return R.layout.activity_identity_select;
    }

    @Override
    public void initData() {
        tvRegisterPhone.setText("选择身份");
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString(Config.USER_NAME,"");
        password = bundle.getString(Config.PASSWORD,"");
    }

    @OnClick({R.id.btn_looking_service, R.id.btn_looking_project})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_looking_service:
                startActivity(Config.PERSON_SINGLE);
                break;
            case R.id.btn_looking_project:
                startActivity(Config.ORDERS_SINGLE);
                break;
        }
    }

    private void startActivity(String bill){
        Bundle bundle = new Bundle();
        bundle.putString(Config.USER_NAME,userName);
        bundle.putString(Config.PASSWORD,password);
        bundle.putString(Config.BILL,bill);
        IntentUtil.startActivity(this,ReceiptOrdersActivity.class,bundle);
    }
}

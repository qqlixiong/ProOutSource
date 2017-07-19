package com.lixiong.straight.login.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.lixiong.straight.MainActivity;
import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.AppManger;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.TransparentStatusBar;
import com.lixiong.straight.login.LoginStringCallback;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;
import okhttp3.Call;

/**
 * 发单和接单
 */
public class ReceiptOrdersActivity extends AppCompatActivity implements IActivity ,TransparentStatusBar {
    private String userName;
    private String password;
    //企业的状态码
    private String enterpriseStatusCode;
    //个人的状态码
    private String individualStatusCode;
    private LoadingDialog loadingDialog;
    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    public int getLayoutId() {
        return R.layout.activity_receipt_orders;
    }

    @Override
    public void initData() {
        sharedPreferenceUtil = new SharedPreferenceUtil(this, Config.SP);
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString(Config.USER_NAME, "");
        password = bundle.getString(Config.PASSWORD, "");
        String bill = bundle.getString(Config.BILL, "");
        if(bill.equals(Config.PERSON_SINGLE)){
            enterpriseStatusCode = Config.PERSON_SINGLE_ENTERPRISE;
            individualStatusCode = Config.PERSON_SINGLE_INDIVIDUAL;
        }else if(bill.equals(Config.ORDERS_SINGLE)){
            enterpriseStatusCode = Config.ORDERS_SINGLE_ENTERPRISE;
            individualStatusCode = Config.ORDERS_SINGLE_INDIVIDUAL;
        }
    }

    @OnClick({R.id.rl_login_back, R.id.btn_enterprise, R.id.btn_individual})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_login_back:
                onBackPressed();
                break;
            case R.id.btn_enterprise:
                okHttpRequest(enterpriseStatusCode);
                break;
            case R.id.btn_individual:
                okHttpRequest(individualStatusCode);
                break;
        }
    }

    private void okHttpRequest(String statusCode){
        loadingDialog = new LoadingDialog(this);
        if(!TextUtils.isEmpty(userName)&&!TextUtils.isEmpty(password)){
            Map<String,String> map = new HashMap<>();
            map.put(Config.USER_NAME_KEY,userName);
            map.put(Config.PASSWORD_KEY,password);
            map.put(Config.STATUS_CODE_KEY,statusCode);
            OkHttpUtils.get().url(URLParam.REGISTER_URL).params(map).
                    build().execute(new ReceiptOrderStringCallback(statusCode));
        }else {
            sharedPreferenceUtil.set(Config.STATUS_CODE_KEY,statusCode);
            Bundle bundle = new Bundle();
            if(Config.PERSON_SINGLE_ENTERPRISE.equals(statusCode)||
                    Config.PERSON_SINGLE_INDIVIDUAL.equals(statusCode)){
                bundle.putBoolean(Config.EXIT_LOGIN,false);
            }else if(Config.ORDERS_SINGLE_ENTERPRISE.equals(statusCode)||
                    Config.ORDERS_SINGLE_INDIVIDUAL.equals(statusCode)){
                bundle.putBoolean(Config.EXIT_LOGIN,true);
            }
            IntentUtil.startActivity(this, MainActivity.class,bundle);
            AppManger.getAppManager().finishAllActivity();
        }
    }

    private class ReceiptOrderStringCallback extends StringCallback{
        //身份状态码
        private String statusCode;

        public ReceiptOrderStringCallback(String statusCode) {
            this.statusCode = statusCode;
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.i("手机号、密码、身份状态码 注册失败"+e);
            loadingDialog.dismiss();
            UIUtils.toast("注册失败");
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.i("手机号、密码、身份状态码 注册成功 response="+response);
            if("1".equals(response)){
                sharedPreferenceUtil.set(Config.STATUS_CODE_KEY,statusCode);
                Map<String,String> map = new HashMap<>();
                map.put(Config.USER_NAME_KEY,userName);
                map.put(Config.PASSWORD_KEY,password);
                OkHttpUtils.get().url(URLParam.USERNAME_PWD_LOGIN).params(map).build().
                        execute(new LoginStringCallback(ReceiptOrdersActivity.this,userName, loadingDialog));
            }
        }
    }
}

package com.lixiong.straight.login.activity;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.PublicTools;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.LoginStringCallback;
import com.lixiong.straight.login.TransparentStatusBar;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.login.viewholder.AgreeProtocolHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class PwdLoginActivity extends AppCompatActivity implements IActivity, TransparentStatusBar {

    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.fl_agree_protocol)
    FrameLayout flAgreeProtocol;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.iv_hide)
    ImageView ivHide;
    private String userName;
    private String password;
    private LoadingDialog loadingDialog;
    private AgreeProtocolHolder agreeProtocolHolder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pwd_login;
    }

    @Override
    public void initData() {
        etPwd.setHint("请输入登录密码");
        agreeProtocolHolder = new AgreeProtocolHolder(this);
        flAgreeProtocol.addView(agreeProtocolHolder.getView());
    }

    @OnClick({R.id.tv_password_login, R.id.tv_forget_pwd, R.id.rl_login_back, R.id.iv_hide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_password_login:
                userPwdLogin();
                break;

            case R.id.tv_forget_pwd:
                IntentUtil.startActivity(this, ForgetActivity.class);
                break;

            case R.id.rl_login_back:
                onBackPressed();
                break;

            case R.id.iv_hide:   //显示和隐藏密码
                PublicTools.setPwdShowHide(etPwd,ivHide);
                break;
        }
    }

    private void userPwdLogin() {
        userName = etPhone.getText().toString();
        password = etPwd.getText().toString();
        if (TextUtils.isEmpty(userName)) {
            UIUtils.toast(R.string.login_phone_empty);
            return;
        }
        if (TextUtils.isEmpty(userName)) {
            UIUtils.toast(R.string.login_phone_format_error);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            UIUtils.toast(R.string.login_pwd_empty);
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(Config.USER_NAME_KEY, userName);
        map.put(Config.PASSWORD_KEY, password);
        loadingDialog = new LoadingDialog(this);
        OkHttpUtils.get().url(URLParam.USERNAME_PWD_LOGIN).params(map).build().
                execute(new LoginStringCallback(PwdLoginActivity.this, userName, loadingDialog));
    }
}

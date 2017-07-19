package com.lixiong.straight.login.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.LoginStringCallback;
import com.lixiong.straight.login.TransparentStatusBar;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.login.viewholder.AgreeProtocolHolder;
import com.lixiong.straight.login.viewholder.ConfirmBtnHolder;
import com.lixiong.straight.login.viewholder.GetCodeHolder;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;

public class LoginActivity extends AppCompatActivity implements IActivity, TransparentStatusBar {
    @Bind(R.id.fl_agree_protocol)
    FrameLayout flAgreeProtocol;
    @Bind(R.id.fl_get_code)
    FrameLayout flGetCode;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.fl_login_btn)
    FrameLayout flLoginBtn;
    private GetCodeHolder getCodeHolder;
    private AgreeProtocolHolder agreeProtocolHolder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {
        agreeProtocolHolder = new AgreeProtocolHolder(this);
        flAgreeProtocol.addView(agreeProtocolHolder.getView());

        getCodeHolder = new GetCodeHolder();
        getCodeHolder.setET(etPhone);
        flGetCode.addView(getCodeHolder.getView());

        ConfirmBtnHolder confirmBtnHolder = new ConfirmBtnHolder(this);
        confirmBtnHolder.setEtPhone(etPhone);
        confirmBtnHolder.setTextBtn(UIUtils.getString(R.string.login));
        confirmBtnHolder.setGetCodeHolder(getCodeHolder);
        flLoginBtn.addView(confirmBtnHolder.getView());
    }

    @OnClick({R.id.rl_login_back, R.id.tv_register, R.id.tv_pwd_login,R.id.textView_tourist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_login_back:
                onBackPressed();
                break;

            case R.id.tv_register:
                IntentUtil.startActivity(this, RegisterActivity.class);
                break;

            case R.id.tv_pwd_login:
                IntentUtil.startActivity(this, PwdLoginActivity.class);
                break;

            case R.id.textView_tourist:
                OkHttpUtils.get().url(URLParam.USERNAME_CODE_URL).addParams(Config.USER_NAME_KEY, "待开发").build().
                        execute(new LoginStringCallback(this, "待开发", new LoadingDialog(this)));
                break;
        }
    }
}

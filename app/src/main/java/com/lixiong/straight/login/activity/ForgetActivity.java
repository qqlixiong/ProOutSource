package com.lixiong.straight.login.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.PublicTools;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.TransparentStatusBar;
import com.lixiong.straight.login.viewholder.AgreeProtocolHolder;
import com.lixiong.straight.login.viewholder.ConfirmBtnHolder;
import com.lixiong.straight.login.viewholder.GetCodeHolder;

import butterknife.Bind;
import butterknife.OnClick;

public class ForgetActivity extends AppCompatActivity implements IActivity, TransparentStatusBar {

    @Bind(R.id.tv_register_phone)
    TextView tvRegisterPhone;
    @Bind(R.id.fl_agree_protocol)
    FrameLayout flAgreeProtocol;
    @Bind(R.id.fl_get_code)
    FrameLayout flGetCode;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.fl_forget_btn)
    FrameLayout flForgetBtn;
    @Bind(R.id.iv_hide)
    ImageView ivHide;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    private String phoneNum;
    private AgreeProtocolHolder agreeProtocolHolder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initData() {
        tvRegisterPhone.setText("忘记密码");
        agreeProtocolHolder = new AgreeProtocolHolder(this);
        flAgreeProtocol.addView(agreeProtocolHolder.getView());

        phoneNum = etPhone.getText().toString();
        GetCodeHolder getCodeHolder = new GetCodeHolder();
        getCodeHolder.setET(etPhone);
        flGetCode.addView(getCodeHolder.getView());

        ConfirmBtnHolder confirmBtnHolder = new ConfirmBtnHolder(this);
        confirmBtnHolder.setEtPhone(etPhone);
        confirmBtnHolder.setTextBtn("重置密码");
        confirmBtnHolder.setGetCodeHolder(getCodeHolder);
        flForgetBtn.addView(confirmBtnHolder.getView());
    }

    @OnClick({R.id.rl_login_back,R.id.iv_hide})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_hide:
                PublicTools.setPwdShowHide(etPwd, ivHide);
                break;

            case R.id.rl_login_back:
                onBackPressed();
                break;
        }
    }
}

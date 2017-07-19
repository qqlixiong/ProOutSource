package com.lixiong.straight.login.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.PublicTools;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.TransparentStatusBar;
import com.lixiong.straight.login.viewholder.AgreeProtocolHolder;
import com.lixiong.straight.login.viewholder.ConfirmBtnHolder;
import com.lixiong.straight.login.viewholder.GetCodeHolder;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements IActivity, TransparentStatusBar {

    @Bind(R.id.fl_agree_protocol)
    FrameLayout flAgreeProtocol;
    @Bind(R.id.fl_get_code)
    FrameLayout flGetCode;
    @Bind(R.id.et_phone)
    EditText etPhone;
    @Bind(R.id.et_pwd)
    EditText etPwd;
    @Bind(R.id.fl_register_btn)
    FrameLayout flRegisterBtn;
    @Bind(R.id.iv_hide)
    ImageView ivHide;
    private GetCodeHolder getCodeHolder;
    private AgreeProtocolHolder agreeProtocolHolder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {
        agreeProtocolHolder = new AgreeProtocolHolder(this);
        flAgreeProtocol.addView(agreeProtocolHolder.getView());

        getCodeHolder = new GetCodeHolder();
        getCodeHolder.setRegister(true);
        getCodeHolder.setET(etPhone);
        flGetCode.addView(getCodeHolder.getView());

        ConfirmBtnHolder confirmBtnHolder = new ConfirmBtnHolder(this);
        confirmBtnHolder.setEtPhone(etPhone);
        confirmBtnHolder.setEtPwd(etPwd);
        confirmBtnHolder.setTextBtn(UIUtils.getString(R.string.register));
        confirmBtnHolder.setGetCodeHolder(getCodeHolder);
        flRegisterBtn.addView(confirmBtnHolder.getView());
    }

    @OnClick({R.id.rl_login_back,R.id.iv_hide})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_hide:
                PublicTools.setPwdShowHide(etPwd, ivHide);
                break;

            case R.id.rl_login_back:
                onBackPressed();
                break;
        }
    }
}

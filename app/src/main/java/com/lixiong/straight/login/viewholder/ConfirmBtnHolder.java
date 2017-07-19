package com.lixiong.straight.login.viewholder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.AppManger;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.DateTimeUtil;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.login.LoginStringCallback;
import com.lixiong.straight.login.Verification;
import com.lixiong.straight.login.activity.IdentitySelectActivity;
import com.lixiong.straight.login.bean.CodeBean;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.my.bean.PersonBean;
import com.lixiong.straight.my.settings.ModifySuccess;
import com.lixiong.straight.my.settings.activity.ModifyMobileNumActivity;
import com.lixiong.straight.my.settings.activity.SettingActivity;
import com.lixiong.straight.view.ButtonView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;

import static com.lixiong.straight.common.utils.UIUtils.runOnUiThread;

/**
 * 确认按钮
 * Created by john on 2017/5/28.
 */

public class ConfirmBtnHolder extends BaseHolder<PersonBean.XmzbAccountEntityCustomBean> implements Verification{
    @Bind(R.id.lrv_btn)
    ButtonView lrvBtn;
    private Context context;
    private EditText etPhone;
    private EditText etPwd;
    private GetCodeHolder getCodeHolder;
    private String textBtnTitle;
    //手机号
    private String phoneNum;
    private boolean isModify;
    //新密码的输入框
    private EditText etPwdNew;
    private String personCode;
    private String etCode = "";
    private String password = "";
    private String newPwd = "";    //新密码
    private LoadingDialog mLoadingDialog;

    public void setEtPwdNew(EditText etPwdNew) {
        this.etPwdNew = etPwdNew;
    }

    public void setModify(boolean modify) {
        isModify = modify;
    }

    public ConfirmBtnHolder(Context context) {
        this.context = context;
    }

    public void setEtPhone(EditText etPhone) {
        this.etPhone = etPhone;
    }

    public void setEtPwd(EditText etPwd) {
        this.etPwd = etPwd;
    }

    public void setPhoneNumber(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setGetCodeHolder(GetCodeHolder getCodeHolder) {
        this.getCodeHolder = getCodeHolder;
    }

    public void setTextBtn(String textBtnTitle){
        this.textBtnTitle = textBtnTitle;
        lrvBtn.setTextBtn(textBtnTitle);
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.login_register_layout);
    }

    @Override
    protected void refreshView(PersonBean.XmzbAccountEntityCustomBean data) {
        personCode = data.getPersonCode();
    }

    @OnClick(R.id.lrv_btn)
    public void onViewClicked() {
        mLoadingDialog = new LoadingDialog(context);
        getCodeHolder.setVerification(this);
        if(etPhone != null){
            phoneNum = etPhone.getText().toString();
        }
        if(isModify){
            etCode = getCodeHolder.getModifyCode().getText().toString();
        }else {
            etCode = getCodeHolder.getEtCode().getText().toString();
        }

        if (TextUtils.isEmpty(phoneNum)) {
            mLoadingDialog.dismiss();
            UIUtils.toast("手机号不能为空");
            return;
        }
        if (TextUtils.isEmpty(etCode)) {
            mLoadingDialog.dismiss();
            UIUtils.toast("验证码不能为空");
            return;
        }

        if(etPwdNew != null){
            newPwd = etPwdNew.getText().toString();
            if(TextUtils.isEmpty(newPwd)){
                mLoadingDialog.dismiss();
                UIUtils.toast("新密码不能为空");
                return;
            }
        }
        if (textBtnTitle.equals(UIUtils.getString(R.string.register))){
            password = etPwd.getText().toString();
            if (TextUtils.isEmpty(password)) {
                mLoadingDialog.dismiss();
                UIUtils.toast("密码不能为空");
                return;
            }
        }
//        CodeBean.XmzbAccountEntityCustomBean codeBean = getCodeHolder.getMCodeBean();
//        if (codeBean != null) {
//            long phoneNumber = codeBean.getCellphone();
//            int code = codeBean.getCodemsg();
//            LogUtil.i("phoneNum="+phoneNum+",phoneNumber="+phoneNumber+",etCode="+etCode+",code"+code);
//            boolean isRightCode = phoneNum.equals(String.valueOf(phoneNumber)) && etCode.equals(String.valueOf(code));
//            LogUtil.i("isRightCode="+isRightCode);
//            if (!isRightCode) {
//                UIUtils.toast("验证码不正确,请重新输入");
//                return;
//            }
//        } else {
//            UIUtils.toast("验证码不正确,请重新输入");
//            return;
//        }
//        if ((DateTimeUtil.getCurrentTimeMillis() - getCodeHolder.getCodeSuccessCurrentTime()) > 10 * 60 * 1000) {
//            UIUtils.toast("验证码已过期,请重新获取");
//            return;
//        }
        SMSSDK.submitVerificationCode("86",phoneNum,etCode);
    }

    public void verificationSubmit() {
        if(textBtnTitle.equals(UIUtils.getString(R.string.login))){
//            loadingDialog = new LoadingDialog(context);
            OkHttpUtils.get().url(URLParam.USERNAME_CODE_URL).addParams(Config.USER_NAME_KEY, phoneNum).build().
                    execute(new LoginStringCallback((Activity) context, phoneNum, mLoadingDialog));
        }else if(textBtnTitle.equals(UIUtils.getString(R.string.register))){
            mLoadingDialog.dismiss();
            Bundle bundle = new Bundle();
            bundle.putString(Config.USER_NAME, phoneNum);
            bundle.putString(Config.PASSWORD, password);
            IntentUtil.startActivity(context, IdentitySelectActivity.class, bundle);
        }else if (textBtnTitle.equals(UIUtils.getString(R.string.modify_next))){
            mLoadingDialog.dismiss();
            Bundle bundle = new Bundle();
            bundle.putBoolean(Config.IS_MODIFY,true);
            IntentUtil.startActivity(context, ModifyMobileNumActivity.class,bundle);
        }else if(textBtnTitle.equals(UIUtils.getString(R.string.modify_confirm))){
//            loadingDialog = new LoadingDialog(context);
            Map<String,String> modifyPhoneMap = new HashMap<>();
            modifyPhoneMap.put("personCode",personCode);
            modifyPhoneMap.put("cellphone",phoneNum);
            OkHttpUtils.get().url(URLParam.REVISE_PHONE).params(modifyPhoneMap)
                    .build().execute(new ReviseStringCallback(Config.MODIFY_PHONE_REQUEST));
        }else if(textBtnTitle.equals(UIUtils.getString(R.string.modify_pwd_confirm))){
//            loadingDialog = new LoadingDialog(context);
            Map<String,String> modifyPwdMap = new HashMap<>();
            modifyPwdMap.put("personCode",personCode);
            modifyPwdMap.put("accPwd",newPwd);
            OkHttpUtils.get().url(URLParam.REVISE_PWD).params(modifyPwdMap)
                    .build().execute(new ReviseStringCallback(Config.MODIFY_PWD_REQUEST));
        }
    }

    @Override
    public void verificationSuccess() {
        verificationSubmit();
    }

    @Override
    public void verificationFail() {
        mLoadingDialog.dismiss();
    }

    private class ReviseStringCallback extends StringCallback{
        private String flag;

        public ReviseStringCallback(String flag) {
            this.flag = flag;
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.e("修改失败！ e:"+e);
            mLoadingDialog.dismiss();
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.e("修改成功！ response："+response);
            mLoadingDialog.dismiss();
            ModifySuccess modifySuccess = JSON.parseObject(response,ModifySuccess.class);
            ModifySuccess.XmzbBackCodesBean xmzbBackCodes = modifySuccess.getXmzbBackCodes();
            int successCode = xmzbBackCodes.getSuccessCode();
            switch (successCode){
                case 201:        //成功
                    List<PersonBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBeanList =
                            PersonBean.XmzbAccountEntityCustomBean.listAll(PersonBean.XmzbAccountEntityCustomBean.class);
                    if (xmzbAccountEntityCustomBeanList.size()>0){
                        PersonBean.XmzbAccountEntityCustomBean person = xmzbAccountEntityCustomBeanList.get(0);
                        if(TextUtils.equals(flag,Config.MODIFY_PHONE_REQUEST)){
                        }else if(TextUtils.equals(flag,Config.MODIFY_PWD_REQUEST)){
                        }
                        IntentUtil.startActivity(context, SettingActivity.class);
                        AppManger.getAppManager().finishActivity((Activity) context,SettingActivity.class);
                    }
                    break;

                case 202:       //失败
                    break;
            }
        }
    }
}

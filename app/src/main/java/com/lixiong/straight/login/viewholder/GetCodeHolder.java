package com.lixiong.straight.login.viewholder;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.CustomCountDownTimer;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.DateTimeUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.RegexUtils;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.login.Verification;
import com.lixiong.straight.login.bean.CodeBean;
import com.lixiong.straight.login.bean.QueryPhoneBean;
import com.lixiong.straight.my.settings.view.ModifyView;
import com.mob.MobSDK;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.Call;
import static com.lixiong.straight.common.utils.UIUtils.runOnUiThread;

/**
 * Created by john on 2017/5/23.
 */

public class GetCodeHolder extends BaseHolder {
    @Nullable
    @Bind(R.id.btn_get_code)
    Button btnGetCode;
    @Nullable
    @Bind(R.id.et_code)
    EditText etCode;
    @Nullable
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Nullable
    @Bind(R.id.rl_code_a)
    RelativeLayout rlCodeA;
    @Nullable
    @Bind(R.id.modify_view)
    ModifyView modifyView;
    private EditText etPhone;
    private CustomCountDownTimer countDownTimer;
    //是否为注册获取验证码
    private boolean isRegister;
    //服务器返回的验证码的json
    private CodeBean.XmzbAccountEntityCustomBean mCodeBean;
    //获取验证码成功的当前时间
    private long getCodeSuccessCurrentTime;
    private String phoneNumber;
    private EventHandler mHandler;
    private Verification mVerification;

    public void setVerification(Verification verification) {
        mVerification = verification;
    }

    public GetCodeHolder(){
        initCountDownTimer();
        initEventHandler();
    }

    private void initEventHandler() {
        MobSDK.init(UIUtils.getContext(), "1f8179b1a8c90","0c01618adb95aac3e65e6039804feecb");
        mHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, final int result, final Object data) {
                if (result == SMSSDK.RESULT_COMPLETE){
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                UIUtils.toast("验证成功");
                                mVerification.verificationSuccess();
                            }
                        });

                    }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                        //获取验证码成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                UIUtils.toast("验证码已发送");
                                showProGUI();
                                LogUtil.i("result:"+result+",data:"+data);
                            }
                        });
                    }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){

                    }
                }else{
                    ((Throwable)data).printStackTrace();
                    Throwable throwable = (Throwable) data;
                    try {
                        JSONObject obj = new JSONObject(throwable.getMessage());
                        final String des = obj.optString("detail");
                        if (!TextUtils.isEmpty(des)){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    UIUtils.toast("提交错误信息");
                                    showProGUI();
                                    mVerification.verificationFail();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        SMSSDK.registerEventHandler(mHandler);
    }

    public GetCodeHolder(final boolean isModify, Drawable drawable){
        ViewUtils.goneView(rlCodeA);
        ViewUtils.showView(modifyView);
        modifyView.setImageDrawable(drawable);
        btnGetCode = modifyView.getBtnGetCode();
        progressBar = modifyView.getProgressBar();
        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputNumber = isModify ? etPhone.getText().toString() : phoneNumber;
                LogUtil.e("isModify:"+isModify+",修改的手机号："+inputNumber);
                getCode(inputNumber);
            }
        });
        initCountDownTimer();
        initEventHandler();
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getCodeSuccessCurrentTime() {
        return getCodeSuccessCurrentTime;
    }

    public CodeBean.XmzbAccountEntityCustomBean getMCodeBean() {
        return mCodeBean;
    }

    public EditText getEtCode() {
        return etCode;
    }

    public EditText getModifyCode(){
        return modifyView.getEtText();
    }

    public void setET(EditText etPhone) {
        this.etPhone = etPhone;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.code_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    public void initCountDownTimer() {
        countDownTimer = new CustomCountDownTimer(60000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                btnGetCode.setBackgroundResource(R.drawable.icon_btn_getverificationcode_s);
                btnGetCode.setText(millisUntilFinished / 1000 + "s");
                btnGetCode.setTextColor(UIUtils.getColor(R.color.white));
                btnGetCode.setEnabled(false);
            }

            @Override
            public void onFinish() {
                btnGetCode.setBackgroundResource(R.drawable.icon_btn_getverificationcode_n);
                btnGetCode.setText("");
                btnGetCode.setEnabled(true);
            }
        };
    }

    private class MyStringCallback extends StringCallback {
        private boolean isQueryPhone;
        private String phoneNum;

        public MyStringCallback(boolean isQueryPhone, String phoneNum) {
            this.isQueryPhone = isQueryPhone;
            this.phoneNum = phoneNum;
        }

        public MyStringCallback() {
        }

        @Override
        public void onError(Call call, Exception e, int id) {
            if (isQueryPhone) {
                LogUtil.i("注册时根据手机号查询用户是否已存在地址失败返回的信息：" + e);
            } else {
                LogUtil.i("获取验证码失败返回的信息：" + e);
            }
            UIUtils.toast("获取验证码失败");
            showProGUI();
        }

        @Override
        public void onResponse(String response, int id) {
            if (isQueryPhone) {
                LogUtil.i("注册时根据手机号查询用户是否已存在地址成功返回的json：" + response);
                QueryPhoneBean queryPhoneBean = JSON.parseObject(response, QueryPhoneBean.class);
                List<QueryPhoneBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBean =
                        queryPhoneBean.getXmzbAccountEntityCustom();
                LogUtil.i("注册时根据手机号查询用户是否已存在的bean数据：" + xmzbAccountEntityCustomBean.toString());
                if (xmzbAccountEntityCustomBean.size() > 0) {
                    long queryPhoneNum = xmzbAccountEntityCustomBean.get(0).getCellphone();
                    if (phoneNum.equals(String.valueOf(queryPhoneNum))) {
                        UIUtils.toast("该用户已注册,请直接登录", true);
                        showProGUI();
                    }
                } else {
                    OkHttpUtils.get().url(URLParam.GET_CODE).addParams(Config.USER_NAME_KEY, phoneNum).
                            build().execute(new MyStringCallback());
                }
            } else {
                LogUtil.i("请求获取验证码成功返回的json：" + response);
                CodeBean codeBean = JSON.parseObject(response, CodeBean.class);
                getCodeSuccessCurrentTime = DateTimeUtil.getCurrentTimeMillis();
                if (codeBean != null) {
                    CodeBean.XmzbAccountEntityCustomBean xmzbAccountEntityCustomBeen =
                            codeBean.getXmzbAccountEntityCustom();
                    if (xmzbAccountEntityCustomBeen != null) {
                        mCodeBean = xmzbAccountEntityCustomBeen;
                    }
                }
                showProGUI();
                countDownTimer.start();
            }
        }
    }

    private void showProGUI() {
        ViewUtils.showView(btnGetCode);
        ViewUtils.goneView(progressBar);
    }

    @OnClick(R.id.btn_get_code)
    public void onViewClicked() {
        String phoneNum = etPhone.getText().toString();
        getCode(phoneNum);
    }

    /**
     * 获取验证码
     * @param phoneNum  手机号
     */
    private void getCode(String phoneNum) {
        if (TextUtils.isEmpty(phoneNum)) {
            UIUtils.toast(R.string.login_phone_empty);
            return;
        }
        if (!RegexUtils.checkMobile(phoneNum)) {
            UIUtils.toast(R.string.login_phone_format_error);
            return;
        }
        ViewUtils.goneView(btnGetCode);
        ViewUtils.showView(progressBar);
//        if (isRegister) {
//            OkHttpUtils.get().url(URLParam.QUERY_PHONE_EXIST).addParams(Config.USER_NAME_KEY, phoneNum).
//                    build().execute(new MyStringCallback(true, phoneNum));
//        } else {
//            OkHttpUtils.get().url(URLParam.GET_CODE).addParams(Config.USER_NAME_KEY, phoneNum).
//                    build().execute(new MyStringCallback());
//        }
        //获取验证码
        SMSSDK.getVerificationCode("86",phoneNum);
        countDownTimer.start();
    }
}

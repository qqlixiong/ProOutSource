package com.lixiong.straight.login;

import android.app.Activity;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.MainActivity;
import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.AppManger;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.my.bean.PersonBean;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

/**
 * 登录请求网络的回调
 * Created by john on 2017/5/24.
 */

public class LoginStringCallback extends StringCallback{
    private Activity activity;
    private SharedPreferenceUtil sharedPreferenceUtil;
    private String userName;
    private LoadingDialog loadingDialog;

    public LoginStringCallback(Activity activity,String userName,LoadingDialog loadingDialog) {
        this.activity = activity;
        this.userName = userName;
        this.loadingDialog = loadingDialog;
        sharedPreferenceUtil = new SharedPreferenceUtil(activity, Config.SP);
    }

    @Override
    public void onError(Call call, Exception e, int id) {
//        UIUtils.toast(R.string.login_error,true);
        LogUtil.i("账号密码登录失败返回的信息：" + e);
        loadingDialog.dismiss();
        loadUserMessage(Constant.getUserMessage());
    }

    @Override
    public void onResponse(String response, int id) {
        LogUtil.i("账号密码登录成功返回的json：" + response);
        if(!TextUtils.isEmpty(response)){
            PersonBean personBean = JSON.parseObject(response,PersonBean.class);
            List<PersonBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBeanList =
                    personBean.getXmzbAccountEntityCustom();
            if(xmzbAccountEntityCustomBeanList.size() > 0){
                loadUserMessage(xmzbAccountEntityCustomBeanList);
            }else {
                UIUtils.toast(R.string.login_error,true);
                loadingDialog.dismiss();
            }
        }
    }

    private void loadUserMessage(List<PersonBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBeanList) {
        UIUtils.toast(UIUtils.getString(R.string.login_success),true);
        sharedPreferenceUtil.set(Config.USER_NAME,userName);
        PersonBean.XmzbAccountEntityCustomBean xmzbAccountEntityCustomBean =
                xmzbAccountEntityCustomBeanList.get(0);
        LogUtil.i("账号密码登录获取的个人信息:"+xmzbAccountEntityCustomBean.toString());
//        xmzbAccountEntityCustomBean.save();
        loadingDialog.dismiss();
        IntentUtil.startActivity(activity, MainActivity.class);
        AppManger.getAppManager().finishAllActivity();
    }
}

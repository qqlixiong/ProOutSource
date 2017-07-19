package com.lixiong.straight.common.utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;

import com.lixiong.straight.App;
import com.lixiong.straight.R;
import com.lixiong.straight.login.activity.LoginActivity;

/**
 * 公用类
 * Created by john on 2017/6/11.
 */

public class PublicTools {
    /**
     * 判断当前的用户是否已登录
     * @return   true为未登录   false为已登录
     */
    public static boolean isToLogin(){
        SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(App.context,Config.SP);
        String userName = sharedPreferenceUtil.get(Config.USER_NAME,"");
        return TextUtils.isEmpty(userName);
    }

    /**
     * 跳转到登录页面
     * @param context   上下文信息
     */
    public static void toLogin(Context context){
        IntentUtil.startActivity(context, LoginActivity.class);
    }

    /**
     * 设置密码的显示和隐藏
     * @param editText     密码输入框
     * @param imageView    显示隐藏密码的图片
     */
    public static void setPwdShowHide(EditText editText, ImageView imageView){
        if(TextUtils.equals((String) imageView.getTag(), UIUtils.getString(R.string.hide))){
            imageView.setImageResource(R.drawable.display);   //显示
            imageView.setTag(UIUtils.getString(R.string.show));
            editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }else if(TextUtils.equals((String) imageView.getTag(), UIUtils.getString(R.string.show))){
            imageView.setImageResource(R.drawable.hide);     //隐藏
            imageView.setTag(UIUtils.getString(R.string.hide));
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}

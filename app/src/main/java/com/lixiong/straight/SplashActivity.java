package com.lixiong.straight;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.lixiong.straight.common.utils.AppManger;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.TransparentStatusBar;

/**
 * 启动页
 */
public class SplashActivity extends AppCompatActivity implements IActivity,TransparentStatusBar{
    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Config.SPLASH_WHAT){
                //根据版本号判断是不是第一次使用
                PackageInfo info=null;
                try {
                    info=getPackageManager().getPackageInfo(getPackageName(),0);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                final float currentVersion = info.versionCode;
                sharedPreferenceUtil = new SharedPreferenceUtil(SplashActivity.this, Config.SP);
                float lastVersion = sharedPreferenceUtil.get(Config.VERSION_KEY, 0f);
                if(currentVersion>lastVersion){
                    //第一次启动将当前版本进行存储
                    sharedPreferenceUtil.set(Config.VERSION_KEY,currentVersion);
                    IntentUtil.startActivity(SplashActivity.this,GuideActivity.class);
                }else {
                    IntentUtil.startActivity(SplashActivity.this,MainActivity.class);
                    AppManger.getAppManager().finishAllActivity();
                }
            }
        }
    };

    @Override
    public void initData() {
        handler.sendEmptyMessageDelayed(Config.SPLASH_WHAT,2000);
    }
}

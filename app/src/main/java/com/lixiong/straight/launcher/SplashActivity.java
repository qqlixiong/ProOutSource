package com.lixiong.straight.launcher;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.lixiong.straight.MainActivity;
import com.lixiong.straight.R;
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
    private boolean isFirstLauncher;
    private MyHandler mHandler;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

//    private static Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            if(msg.what == Config.SPLASH_WHAT){
//                startNextActivity();
//            }
//        }
//    };

    //注意：这里启动不能使用单例
    private void startNextActivity(Context context) {
        if (!isFirstLauncher){
            isFirstLauncher = true;
            //根据版本号判断是不是第一次使用
            PackageInfo info=null;
            try {
                info=getPackageManager().getPackageInfo(getPackageName(),0);

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if(info != null){
                final float currentVersion = info.versionCode;
                sharedPreferenceUtil = new SharedPreferenceUtil(context, Config.SP);
                float lastVersion = sharedPreferenceUtil.get(Config.VERSION_KEY, 0f);
                if(currentVersion>lastVersion){
                    //第一次启动将当前版本进行存储
                    sharedPreferenceUtil.set(Config.VERSION_KEY,currentVersion);
                    IntentUtil.startActivity(context,GuideActivity.class);
                }else {
                    IntentUtil.startActivity(context,MainActivity.class);
                }
                AppManger.getAppManager().finishAllActivity();
            }
        }
    }

    @Override
    public void initData() {
        mHandler = new MyHandler(this);
//        handler.sendEmptyMessageDelayed(Config.SPLASH_WHAT,2000);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startNextActivity(mHandler.getActivity());
            }
        },2000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startNextActivity(SplashActivity.this);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}

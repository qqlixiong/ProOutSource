package com.lixiong.straight;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.mob.MobSDK;
import com.orm.Database;
import com.orm.SugarApp;

import java.util.ArrayList;
import java.util.List;

import cn.smssdk.SMSSDK;

/**
 * Created by john on 2017/5/20.
 */

public class App extends SugarApp{
    //在整个应用执行过程中，需要提供的变量
    public static Context context;//需要使用的上下文对象：application实例
    public static Handler handler;//需要使用的handler
    public static Thread mainThread;//提供主线程对象
    public static int mainThreadId;//提供主线程对象的id
    public static List<?> images=new ArrayList<>();
    public static int H;

    protected String a() {
        return null;
    }

    protected String b() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        MobSDK.init(this, this.a(), this.b());
        handler = new Handler();
        mainThread = Thread.currentThread();//实例化当前Application的线程即为主线程
        mainThreadId = android.os.Process.myTid();//获取当前线程的id
        H=getScreenH(this);
        com.lixiong.straight.common.ActivityLifecycleCallbacks.getInstance().registerActivityLifecycleCallbacks(this);
    }

    public int getScreenH(Context aty) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}

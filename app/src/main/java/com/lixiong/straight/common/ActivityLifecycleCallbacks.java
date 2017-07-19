package com.lixiong.straight.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.lixiong.straight.App;
import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.AppManger;
import com.lixiong.straight.common.utils.RecyclerViewUtil;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.interfaces.IRecyclerViewActivity;
import com.lixiong.straight.login.TransparentStatusBar;

import butterknife.ButterKnife;
import cn.smssdk.SMSSDK;

/**
 * Created by john on 2017/5/18.
 */

public class ActivityLifecycleCallbacks {

    private ActivityLifecycleCallbacks() {
        if(Inner.activityLifecycleCallbacks != null){
            throw new RuntimeException();
        }
    }

    private static class Inner{
        private static ActivityLifecycleCallbacks activityLifecycleCallbacks = new ActivityLifecycleCallbacks();
    }

    public static ActivityLifecycleCallbacks getInstance(){
        return Inner.activityLifecycleCallbacks;
    }

    public void registerActivityLifecycleCallbacks(App app) {
        app.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if(activity instanceof IActivity){
                    activity.setContentView(((IActivity) activity).getLayoutId());
                    ButterKnife.bind(activity);
                    ((IActivity) activity).initData();
                    if(activity instanceof IRecyclerViewActivity){
                        ((IRecyclerViewActivity) activity).initRecyclerView(RecyclerViewUtil.getInstance());
                    }
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {
                AppManger.getAppManager().addActivity(activity);
                if(activity instanceof TransparentStatusBar){
                    ImmersionBar.with(activity).transparentBar().statusBarDarkFont(true).init();
                }else {
                    ImmersionBar.with(activity).barColor(R.color.color_status_bar).init();
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ButterKnife.unbind(activity);
                ImmersionBar.with(activity).destroy();
                SMSSDK.unregisterAllEventHandler();
            }
        });
    }
}

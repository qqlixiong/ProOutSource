package com.lixiong.straight.common.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * 应用程序Activity管理工具类，用于Activity的管理和应用程序的退出
 */
public class AppManger {
    private static Stack<Activity> activityStack;

    private AppManger() {
        if(Inner.appManger != null){
            throw new RuntimeException();
        }
    }

    private static class Inner{
        private static AppManger appManger = new AppManger();
    }

    public static AppManger getAppManager() {
        return Inner.appManger;
    }

    /**
     * 添加Activity 到栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前的Activity（堆栈中最后一个压入的)
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activityOne,Class<?>... clsList) {
        if (activityOne != null) {
            activityStack.remove(activityOne);
            activityOne.finish();
        }
        if(clsList.length>0){
            for(Activity activity : activityStack){
                for(Class<?> cls : clsList){
                    if(activity.getClass().equals(cls)){
                        activity.finish();
                    }
                }
            }
        }
    }

    /**
     * 结束所有的Activity、
     */
    public void finishAllActivity() {
        int size = activityStack.size();
        for (int i = 0; i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public void appExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {

        }
    }
}

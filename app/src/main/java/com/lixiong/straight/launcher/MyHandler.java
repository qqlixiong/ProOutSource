package com.lixiong.straight.launcher;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

/**
 * 使用弱引用有效的避免了Handler内存泄漏问题
 * Created by Administrator on 2017/7/22.
 */

public class MyHandler extends Handler{
    private WeakReference<AppCompatActivity> mActivity;

    public MyHandler(AppCompatActivity activity) {
        mActivity = new WeakReference<>(activity);
    }

    public AppCompatActivity getActivity(){
        return mActivity.get();
    }
}

package com.lixiong.straight.my.notify;

import android.support.v7.app.AppCompatActivity;

import com.lixiong.straight.R;
import com.lixiong.straight.interfaces.IActivity;

/**
 * 通知
 */
public class NotifyActivity extends AppCompatActivity implements IActivity{

    @Override
    public int getLayoutId() {
        return R.layout.activity_notify;
    }

    @Override
    public void initData() {

    }
}

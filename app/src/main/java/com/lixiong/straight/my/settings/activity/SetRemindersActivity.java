package com.lixiong.straight.my.settings.activity;

import android.support.v7.app.AppCompatActivity;

import com.lixiong.straight.R;
import com.lixiong.straight.interfaces.IActivity;

/**
 * 设置提醒
 */
public class SetRemindersActivity extends AppCompatActivity implements IActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_reminders;
    }

    @Override
    public void initData() {
    }
}

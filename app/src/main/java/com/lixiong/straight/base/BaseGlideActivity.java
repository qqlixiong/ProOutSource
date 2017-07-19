package com.lixiong.straight.base;

import android.support.v7.app.AppCompatActivity;

import com.lixiong.straight.common.GlideImage;
import com.lixiong.straight.interfaces.IActivity;

/**
 * Created by john on 2017/6/11.
 */

public abstract class BaseGlideActivity extends AppCompatActivity implements IActivity {
    protected GlideImage glideImage;

    @Override
    public void initData() {
        glideImage = GlideImage.getInstance();
    }
}

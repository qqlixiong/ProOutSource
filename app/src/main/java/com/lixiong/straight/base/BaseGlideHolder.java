package com.lixiong.straight.base;

import android.content.Context;

import com.lixiong.straight.common.GlideImage;

/**
 * Created by john on 2017/6/11.
 */

public abstract class BaseGlideHolder<T> extends BaseHolder<T>{
    public abstract void initGlideImage(Context context,T data, GlideImage glideImage);
}

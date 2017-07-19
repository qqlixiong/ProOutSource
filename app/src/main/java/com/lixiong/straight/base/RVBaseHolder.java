package com.lixiong.straight.base;

import com.lixiong.straight.common.utils.RecyclerViewUtil;

/**
 * Created by john on 2017/5/21.
 */

public abstract class RVBaseHolder<T> extends BaseHolder<T>{
    protected RecyclerViewUtil recyclerViewUtil;

    @Override
    protected void init() {
        super.init();
        recyclerViewUtil = RecyclerViewUtil.getInstance();
    }
}

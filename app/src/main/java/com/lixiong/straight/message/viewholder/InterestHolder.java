package com.lixiong.straight.message.viewholder;

import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.UIUtils;

/**
 * Created by john on 2017/5/21.
 */

public class InterestHolder extends BaseHolder{
    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.interest_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }
}

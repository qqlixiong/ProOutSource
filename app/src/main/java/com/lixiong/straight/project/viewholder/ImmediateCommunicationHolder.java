package com.lixiong.straight.project.viewholder;

import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.UIUtils;

/**
 * Created by john on 2017/5/22.
 */

public class ImmediateCommunicationHolder extends BaseHolder{
    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.imm_com_chat_msg_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }
}

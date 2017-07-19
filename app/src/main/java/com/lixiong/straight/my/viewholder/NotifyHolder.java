package com.lixiong.straight.my.viewholder;

import android.content.Context;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.notify.NotifyActivity;

import butterknife.OnClick;

/**
 * 通知
 * Created by john on 2017/5/21.
 */

public class NotifyHolder extends BaseHolder {
    private Context context;

    public NotifyHolder(Context context) {
        this.context = context;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.my_notify_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick(R.id.mv_notify)
    public void onViewClicked() {
        IntentUtil.startActivity(context, NotifyActivity.class);
    }
}

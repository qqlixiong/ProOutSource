package com.lixiong.straight.my.viewholder;

import android.content.Context;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.transaction.TransactionDetailActivity;

import butterknife.OnClick;

/**
 * Created by john on 2017/5/21.
 */

public class TransactionDetailHolder extends BaseHolder {
    private Context context;

    public TransactionDetailHolder(Context context) {
        this.context = context;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.my_transaction_detail_layout);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick(R.id.mv_transaction_detail)
    public void onViewClicked() {
        IntentUtil.startActivity(context, TransactionDetailActivity.class);
    }
}

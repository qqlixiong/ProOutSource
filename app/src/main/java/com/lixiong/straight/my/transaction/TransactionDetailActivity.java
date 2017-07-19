package com.lixiong.straight.my.transaction;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.my.view.NoDataView;
import com.lixiong.straight.view.TopBar;

import butterknife.Bind;

/**
 * 交易明细
 */
public class TransactionDetailActivity extends AppCompatActivity implements IActivity, View.OnClickListener {

    @Bind(R.id.top_transaction_detail)
    TopBar topTransactionDetail;
    @Bind(R.id.ndv_transaction_detail)
    NoDataView ndvTransactionDetail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_transaction_detail;
    }

    @Override
    public void initData() {
        topTransactionDetail.getIvSave().setImageDrawable(UIUtils.getDrawable(R.drawable.question_mark));
        topTransactionDetail.getRlSave().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        IntentUtil.startActivity(this,MoneyExplainActivity.class);
    }
}

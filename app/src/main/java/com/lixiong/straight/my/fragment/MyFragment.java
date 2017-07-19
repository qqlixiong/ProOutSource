package com.lixiong.straight.my.fragment;


import android.widget.FrameLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseFragment;
import com.lixiong.straight.my.experience.viewholder.ProExperienceHolder;
import com.lixiong.straight.my.viewholder.MyHolder;
import com.lixiong.straight.my.viewholder.NotifyHolder;
import com.lixiong.straight.my.viewholder.TransactionDetailHolder;

import butterknife.Bind;

/**
 * 我的
 */
public class MyFragment extends BaseFragment {

    @Bind(R.id.fl_my)
    FrameLayout flMy;
    @Bind(R.id.fl_notify)
    FrameLayout flNotify;               //通知
    @Bind(R.id.fl_pro_experience)
    FrameLayout flProExperience;       //项目经验
    @Bind(R.id.fl_transaction_detail)
    FrameLayout flTransactionDetail;   //交易明细

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        super.initView();

        MyHolder myHolder = new MyHolder(mContext);
        flMy.addView(myHolder.getView());

        NotifyHolder notifyHolder = new NotifyHolder(mContext);
        flNotify.addView(notifyHolder.getView());

        ProExperienceHolder proExperienceHolder = new ProExperienceHolder(mContext);
        flProExperience.addView(proExperienceHolder.getView());

        TransactionDetailHolder transactionDetailHolder = new TransactionDetailHolder(mContext);
        flTransactionDetail.addView(transactionDetailHolder.getView());
    }
}

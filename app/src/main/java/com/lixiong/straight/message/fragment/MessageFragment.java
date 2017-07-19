package com.lixiong.straight.message.fragment;


import android.widget.FrameLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseFragment;
import com.lixiong.straight.message.viewholder.CommunicateHolder;
import com.lixiong.straight.message.viewholder.InterestHolder;
import com.lixiong.straight.message.viewholder.ListMessageHolder;

import butterknife.Bind;

/**
 * 消息
 */
public class MessageFragment extends BaseFragment {

    @Bind(R.id.fl_interest)
    FrameLayout flInterest;      //我的关注
    @Bind(R.id.fl_communicate)
    FrameLayout flCommunicate;   //沟通
    @Bind(R.id.fl_list_message)
    FrameLayout flListMessage;   //消息列表

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView() {
        super.initView();
        InterestHolder interestHolder = new InterestHolder();
        flInterest.addView(interestHolder.getView());

        CommunicateHolder communicateHolder = new CommunicateHolder();
        flCommunicate.addView(communicateHolder.getView());

        ListMessageHolder listMessageHolder = new ListMessageHolder(mContext);
        flListMessage.addView(listMessageHolder.getView());
    }

}

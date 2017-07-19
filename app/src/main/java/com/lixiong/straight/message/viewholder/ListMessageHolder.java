package com.lixiong.straight.message.viewholder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.RVBaseHolder;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.message.holder.MessageHolder;
import com.lixiong.straight.view.DividerListItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by john on 2017/5/21.
 */

public class ListMessageHolder extends RVBaseHolder {
    @Bind(R.id.rv_list_message)
    RecyclerView rvListMessage;

    public ListMessageHolder(Context context) {
        recyclerViewUtil.initRecyclerView(rvListMessage, Constant.getMessagePerName(),new LinearLayoutManager(context,
                        LinearLayoutManager.VERTICAL,false),
                new DividerListItemDecoration(context,DividerListItemDecoration.VERTICAL_LIST,
                        true),MessageHolder.class);
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.list_message_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }
}

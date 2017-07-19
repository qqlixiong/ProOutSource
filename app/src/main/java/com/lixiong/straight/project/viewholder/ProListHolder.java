package com.lixiong.straight.project.viewholder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.RVBaseHolder;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.my.view.NoDataView;
import com.lixiong.straight.project.bean.ProMsg;
import com.lixiong.straight.project.holder.ProRVHolder;

import java.util.List;

import butterknife.Bind;

/**
 * Created by john on 2017/5/21.
 */

public class ProListHolder extends RVBaseHolder<List<ProMsg.ProjectsEntity>> {
    @Bind(R.id.rv_pro)
    RecyclerView rvPro;
    @Nullable
    @Bind(R.id.ndv_project_list)
    NoDataView ndvProjectList;
    private Context context;

    public ProListHolder(Context context) {
        this.context = context;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.pro_list_layout);
    }

    @Override
    protected void refreshView(List<ProMsg.ProjectsEntity> data) {
        if(data.size()==0){
            ViewUtils.showView(ndvProjectList);
            ViewUtils.goneView(rvPro);
        }else {
            ViewUtils.goneView(ndvProjectList);
            ViewUtils.showView(rvPro);
            recyclerViewUtil.initRecyclerView(rvPro, data,
                    new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false), ProRVHolder.class);
        }
    }
}

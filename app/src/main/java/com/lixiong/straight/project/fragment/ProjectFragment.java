package com.lixiong.straight.project.fragment;


import android.content.Context;
import android.database.SQLException;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseFragment;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.project.bean.ProMsg;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.my.view.NoDataView;
import com.lixiong.straight.project.viewholder.ProHeadHolder;
import com.lixiong.straight.project.viewholder.ProListHolder;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * 项目
 */
public class ProjectFragment extends BaseFragment {

    @Bind(R.id.fl_search_layout)
    FrameLayout flSearchLayout;
    @Bind(R.id.fl_head_layout)
    FrameLayout flHeadLayout;
    @Bind(R.id.fl_pro_list_layout)
    FrameLayout flProListLayout;
    @Nullable
    @Bind(R.id.ndv_project)
    NoDataView ndvProject;
    private LoadingDialog loadingDialog;
    private SharedPreferenceUtil sharedPreferenceUtil;
    private ProListHolder proListHolder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.e("onAttach(Context context)");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 根据所选择的城市更新UI
     * @param clubName       请求结果
     */
    public void upDataUI(String clubName){
        LogUtil.e("upDataUI(String clubName):"+clubName);
        ProMsg proMsg = JSON.parseObject(clubName,ProMsg.class);
        List<ProMsg.ProjectsEntity> projectsEntityList = proMsg.getXmzbProjectsEntityCustom();
        proListHolder.setData(projectsEntityList);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Config.PROJECT_CATEGORY_WHAT:
                    proListHolder.setData((List<ProMsg.ProjectsEntity>) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initData() {
        super.initData();
        loadingDialog = new LoadingDialog(mContext);
        sharedPreferenceUtil = new SharedPreferenceUtil(mContext,Config.SP);
        boolean isLauncherSuccess = sharedPreferenceUtil.get(Config.FIRST_LAUNCHER_SUCCESS, false);
        if (isLauncherSuccess) {
            try {
                List<ProMsg.ProjectsEntity> xmzbProjectsEntityCustomBeanList =
                        ProMsg.ProjectsEntity.listAll(ProMsg.ProjectsEntity.class);
                if (xmzbProjectsEntityCustomBeanList.size() > 0) {
                    load(xmzbProjectsEntityCustomBeanList);
                } else {
                    ViewUtils.showView(ndvProject);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                ViewUtils.showView(ndvProject);
            }
        } else {
            okHttpRequestAllPro();
        }
    }

    private void okHttpRequestAllPro() {
        OkHttpUtils.get().url(URLParam.QUERY_ALL_PROJECT).build().execute(new QueryAllProStringCallback());
    }

    private class QueryAllProStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.i("查询所有数据信息失败:" + e);
//            ViewUtils.showView(ndvProject);
            loadingDialog.dismiss();
            load(Constant.getProData());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.i("查询所有数据信息成功 response=" + response);
            ProMsg proMsg = JSON.parseObject(response, ProMsg.class);
            List<ProMsg.ProjectsEntity> xmzbProjectsEntityCustomBeanList =
                    proMsg.getXmzbProjectsEntityCustom();
            if (xmzbProjectsEntityCustomBeanList.size() > 0) {
                ProMsg.ProjectsEntity.deleteAll(ProMsg.ProjectsEntity.class);
                for (ProMsg.ProjectsEntity xmzbPECBean : xmzbProjectsEntityCustomBeanList) {
                    xmzbPECBean.save();
                }
                sharedPreferenceUtil.set(Config.FIRST_LAUNCHER_SUCCESS, true);
                load(xmzbProjectsEntityCustomBeanList);
            }
        }
    }

    private void load(List<ProMsg.ProjectsEntity> xmzbProjectsEntityCustomBeanList) {
        loadingDialog.dismiss();
        ViewUtils.goneView(ndvProject);
        ProHeadHolder proHeadHolder = new ProHeadHolder(mContext,handler);
        flHeadLayout.addView(proHeadHolder.getView());

        proListHolder = new ProListHolder(mContext);
        proListHolder.setData(xmzbProjectsEntityCustomBeanList);
        flProListLayout.addView(proListHolder.getView());
    }
}

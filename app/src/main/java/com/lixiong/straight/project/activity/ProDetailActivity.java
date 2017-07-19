package com.lixiong.straight.project.activity;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseGlideActivity;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.PublicTools;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.my.view.NoDataView;
import com.lixiong.straight.project.bean.ProDetail;
import com.lixiong.straight.project.viewholder.ProDetailHolder;
import com.lixiong.straight.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

public class ProDetailActivity extends BaseGlideActivity {

    @Nullable
    @Bind(R.id.fl_pro_detail)
    FrameLayout flProDetail;
    @Nullable
    @Bind(R.id.tv_immediate_communication)
    TextView tvImmediateCommunication;
    @Bind(R.id.ndv_pro_detail)
    NoDataView ndvProDetail;
    @Bind(R.id.pro_detail_title)
    TopBar proDetailTitle;
    private LoadingDialog loadingDialog;
    private String accSysCode;
    private ImageView ivCollect;    //收藏按钮
    private ImageView ivShare;      //分享按钮

    @Override
    public int getLayoutId() {
        return R.layout.activity_pro_detail;
    }

    @Override
    public void initData() {
        super.initData();
        loadingDialog = new LoadingDialog(this);
        accSysCode = getIntent().getExtras().getString(Config.ACC_SYS_CODE, "");
        ivShare = proDetailTitle.getIvTopA();
        ivCollect = proDetailTitle.getIvTopB();
        ivShare.setImageResource(R.drawable.share);
        ivCollect.setImageResource(R.drawable.collect);
        OkHttpUtils.get().url(URLParam.QUERY_PROJECT_FIND)
                .addParams("accSyscode", accSysCode)
                .build().execute(new QueryProDetailStringCallback());
    }

    @Nullable
    @OnClick({R.id.tv_immediate_communication,R.id.iv_top_a,R.id.iv_top_b})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_immediate_communication:

                if(PublicTools.isToLogin()){
                    PublicTools.toLogin(this);
                }else {
                    IntentUtil.startActivity(this, ImmComActivity.class);
                }
                break;

            case R.id.iv_top_a:
                if(PublicTools.isToLogin()){
                    PublicTools.toLogin(this);
                }
                break;

            case R.id.iv_top_b:
                if(PublicTools.isToLogin()){
                    PublicTools.toLogin(this);
                }
                break;
        }
    }

    public class QueryProDetailStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.i("获取项目详情信息失败：" + e);
            loadingDialog.dismiss();
//            loadNoDate();

            loadProDetail(Constant.getProDetail());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.i("获取项目详情信息成功  response=" + response);
            ProDetail proDetail = JSON.parseObject(response, ProDetail.class);
            List<ProDetail.ProjectsDetailEntity> projectsDetailEntityList = proDetail.getXmzbProjectsEntityCustom();
            if (projectsDetailEntityList.size() > 0) {
                loadingDialog.dismiss();
                loadProDetail(projectsDetailEntityList);
            } else {
                loadNoDate();
            }
        }
    }

    private void loadProDetail(List<ProDetail.ProjectsDetailEntity> projectsDetailEntityList) {
        ViewUtils.goneView(ndvProDetail);
        ViewUtils.showView(flProDetail, tvImmediateCommunication);
        ProDetail.ProjectsDetailEntity projectsDetailEntity = projectsDetailEntityList.get(0);
        ProDetailHolder proDetailHolder = new ProDetailHolder();
        proDetailHolder.setData(projectsDetailEntity);
        proDetailHolder.initGlideImage(ProDetailActivity.this, projectsDetailEntity, glideImage);
        flProDetail.addView(proDetailHolder.getView());
    }

    private void loadNoDate() {
        loadingDialog.dismiss();
        ViewUtils.showView(ndvProDetail);
        ViewUtils.goneView(flProDetail);
    }
}

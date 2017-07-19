package com.lixiong.straight.project.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.my.bean.ServiceCategory;
import com.lixiong.straight.my.view.NoDataView;
import com.lixiong.straight.project.bean.ProMsg;
import com.lixiong.straight.project.viewholder.ProListHolder;
import com.lixiong.straight.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * 搜索结果
 */
public class SearchResultActivity extends AppCompatActivity implements IActivity, View.OnClickListener {

    @Bind(R.id.top_search_result)
    TopBar topSearchResult;
    @Nullable
    @Bind(R.id.fl_search_result)
    FrameLayout flSearchResult;
    @Nullable
    @Bind(R.id.ndv_search_result)
    NoDataView ndvSearchResult;
    private LoadingDialog loadingDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    public void initData() {
        TextView topCancel = topSearchResult.getTvSave();
        topCancel.setText(UIUtils.getString(R.string.cancel));
        Bundle searchBundle = getIntent().getExtras();
        String searchContent = searchBundle.getString(Config.SEARCH, "");
        ServiceCategory serviceCategory = (ServiceCategory) searchBundle.getSerializable(Config.SEARCH_CATEGORY);
        loadingDialog = new LoadingDialog(this);
        if(!TextUtils.isEmpty(searchContent)){
            save(searchContent);
            okHttpRequest("proname",searchContent);
        }
        if(serviceCategory != null){
            save(serviceCategory.getItemName());
            okHttpRequest("itemCode",serviceCategory.getItemCode());
        }
        topCancel.setOnClickListener(this);
    }

    private void okHttpRequest(String key,String value) {
        OkHttpUtils.get().url(URLParam.QUERY_BY_CONDITION)
                .addParams(key,value).build()
                .execute(new CategoryStringCallback());
    }

    private void save(String value) {
        SearchText searchText = new SearchText();
        searchText.setSearchText(value);
        LogUtil.i("搜索的内容：" + searchText.toString());
        searchText.save();
    }

    private class CategoryStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.i("查询类别数据失败 "+e);
            loadingDialog.dismiss();
//            ViewUtils.showView(ndvSearchResult);
//            ViewUtils.goneView(flSearchResult);
            ProMsg proMsg = new ProMsg();
            proMsg.setXmzbProjectsEntityCustom(Constant.getProData());
            loadSearchResultData(proMsg);
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.i("查询类别数据成功 response"+response);
            loadingDialog.dismiss();
            ProMsg proMsg = JSON.parseObject(response, ProMsg.class);
            loadSearchResultData(proMsg);
        }
    }

    private void loadSearchResultData(ProMsg proMsg) {
        ViewUtils.showView(flSearchResult);
        ViewUtils.goneView(ndvSearchResult);
        List<ProMsg.ProjectsEntity> projectsEntityList = proMsg.getXmzbProjectsEntityCustom();
        ProListHolder proListHolder = new ProListHolder(SearchResultActivity.this);
        proListHolder.setData(projectsEntityList);
        flSearchResult.addView(proListHolder.getView());
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}

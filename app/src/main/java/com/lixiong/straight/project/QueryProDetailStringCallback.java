package com.lixiong.straight.project;

import android.content.Context;

import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by john on 2017/6/10.
 */

public class QueryProDetailStringCallback extends StringCallback{
    private Context context;
    private LoadingDialog loadingDialog;

    public QueryProDetailStringCallback(Context context, LoadingDialog loadingDialog) {
        this.context = context;
        this.loadingDialog = loadingDialog;
    }

    @Override
    public void onError(Call call, Exception e, int id) {
        LogUtil.i("获取项目详情信息失败："+e);
    }

    @Override
    public void onResponse(String response, int id) {
        LogUtil.i("获取项目详情信息成功  response="+response);
    }
}

package com.lixiong.straight.my.prop.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.FrameLayout;

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
import com.lixiong.straight.my.prop.holder.SPHolder;
import com.lixiong.straight.my.prop.bean.CardDetail;
import com.lixiong.straight.my.view.NoDataView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

public class ShoppingPropDetailActivity extends AppCompatActivity implements IActivity {
    @Nullable
    @Bind(R.id.ndv_shopping_prop_detail)
    NoDataView ndvShoppingPropDetail;
    @Bind(R.id.fl_shopping_prop_content)
    FrameLayout flShoppingPropContent;
    private LoadingDialog loadingDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopping_prop_detail;
    }

    @Override
    public void initData() {
        loadingDialog = new LoadingDialog(this);
        String toolCode = getIntent().getExtras().getString(Config.SHOPPING_PROP_TOOL_CODE);
        OkHttpUtils.get().url(URLParam.QUERY_PROP).addParams("toolCode",toolCode)
                .build().execute(new SPStringCallback());
    }

    private class SPStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.i("根据道具编码查询某一条道具信息失败:"+e);
//            UIUtils.toast("获取道具失败");
            loadingDialog.dismiss();
//            loadNoDataView();
            loadData(Constant.getCardDetail());
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.i("根据道具编码查询某一条道具信息成功:response="+response);
            if(!TextUtils.isEmpty(response)){
                CardDetail cardDetail = JSON.parseObject(response,CardDetail.class);
                List<CardDetail.XmzbToolsEntityCustomBean> xmzbToolsEntityCustomBeen =
                        cardDetail.getXmzbToolsEntityCustom();
                if(xmzbToolsEntityCustomBeen.size()>0){
                    CardDetail.XmzbToolsEntityCustomBean card = xmzbToolsEntityCustomBeen.get(0);
                    loadData(card);
                }else {
                    UIUtils.toast("获取道具失败");
                    loadNoDataView();
                }
            }else {
                UIUtils.toast("获取道具失败");
                loadNoDataView();
            }
            loadingDialog.dismiss();
        }
    }

    private void loadData(CardDetail.XmzbToolsEntityCustomBean card) {
        ViewUtils.goneView(ndvShoppingPropDetail);
        ViewUtils.showView(flShoppingPropContent);
        SPHolder spHolder = new SPHolder(this);
        spHolder.setData(card);
        flShoppingPropContent.addView(spHolder.getView());
    }

    private void loadNoDataView() {
        ViewUtils.showView(ndvShoppingPropDetail);
        ViewUtils.goneView(flShoppingPropContent);
    }
}

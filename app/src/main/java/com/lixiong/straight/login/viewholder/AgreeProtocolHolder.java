package com.lixiong.straight.login.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.login.activity.ServiceProtocolActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by john on 2017/5/21.
 */

public class AgreeProtocolHolder extends BaseHolder {
    @Bind(R.id.tv_agree_login)
    TextView tvAgreeLogin;
    @Bind(R.id.tv_ser_protocol)
    TextView tvSerProtocol;
    private Context context;

    public AgreeProtocolHolder(Context context) {
        this.context = context;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.login_bottom_layout);
    }

    @Override
    protected void init() {
        super.init();
        tvAgreeLogin.setText("登录即代表您同意\"项目外包\"的");
        UIUtils.setUnderline(tvSerProtocol,R.color.color_status_bar);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick(R.id.tv_ser_protocol)
    public void onViewClicked() {
        IntentUtil.startActivity(context, ServiceProtocolActivity.class);
    }
}

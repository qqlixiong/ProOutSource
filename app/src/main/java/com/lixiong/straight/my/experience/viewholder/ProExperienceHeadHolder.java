package com.lixiong.straight.my.experience.viewholder;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import com.lixiong.straight.App;
import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.view.TopBar;

import butterknife.Bind;

/**
 * 项目经验顶部布局
 * Created by john on 2017/5/26.
 */

public class ProExperienceHeadHolder extends BaseHolder implements View.OnClickListener {

    @Bind(R.id.top_pro_experience)
    TopBar topProExperience;
    private Activity activity;

    public ProExperienceHeadHolder(Activity activity) {
        this.activity = activity;
        topProExperience.getRlTopBack().setOnClickListener(this);
    }

    @Override
    protected View initView() {
        SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(App.context, Config.SP);
        String statusCode = sharedPreferenceUtil.get(Config.STATUS_CODE_KEY, "");
        if (!TextUtils.isEmpty(statusCode)) {
            if (statusCode.equals(Config.PERSON_SINGLE_ENTERPRISE) || statusCode.equals(Config.PERSON_SINGLE_INDIVIDUAL)) {
                return UIUtils.inflate(R.layout.receipt_pro_experience_head_layout);
            }
        }
        return UIUtils.inflate(R.layout.orders_pro_experience_head_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @Override
    public void onClick(View v) {
        activity.onBackPressed();
    }
}

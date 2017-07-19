package com.lixiong.straight.my.experience.viewholder;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.lixiong.straight.App;
import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.PublicTools;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.experience.activity.AddProExperienceActivity;
import com.lixiong.straight.my.experience.activity.IssueProjectActivity;

import butterknife.OnClick;

/**
 * 项目经验内容布局
 * Created by john on 2017/5/26.
 */

public class ProExperienceContentHolder extends BaseHolder {
    private SharedPreferenceUtil sharedPreferenceUtil;
    private String statusCode;
    private Context context;

    public ProExperienceContentHolder(Context context) {
        this.context = context;
    }

    @Override
    protected View initView() {
        sharedPreferenceUtil = new SharedPreferenceUtil(App.context, Config.SP);
        statusCode = sharedPreferenceUtil.get(Config.STATUS_CODE_KEY, "");
        if (!TextUtils.isEmpty(statusCode)) {
            if (statusCode.equals(Config.PERSON_SINGLE_ENTERPRISE) || statusCode.equals(Config.PERSON_SINGLE_INDIVIDUAL)) {
                return UIUtils.inflate(R.layout.receipt_pro_experience_content_a_layout);
            }
        }
        return UIUtils.inflate(R.layout.orders_pro_experience_content_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @Nullable
    @OnClick({R.id.iv_issue_project,R.id.iv_add_pro_experience})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_issue_project:
                IntentUtil.startActivity(context, IssueProjectActivity.class);
                break;

            case R.id.iv_add_pro_experience:
                if (PublicTools.isToLogin()){
                    PublicTools.toLogin(context);
                }else {
                    IntentUtil.startActivity(context,AddProExperienceActivity.class);
                }
                break;
        }
    }
}

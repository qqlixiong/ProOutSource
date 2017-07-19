package com.lixiong.straight.my.experience.viewholder;

import android.content.Context;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.experience.activity.ProExperienceActivity;
import com.lixiong.straight.my.view.MyView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 项目经验
 * Created by john on 2017/5/21.
 */

public class ProExperienceHolder extends BaseHolder {
    @Bind(R.id.mv_pro_experience)
    MyView mvProExperience;
    private Context context;

    public ProExperienceHolder(Context context) {
        this.context = context;
        SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(context, Config.SP);
        String statusCode = sharedPreferenceUtil.get(Config.STATUS_CODE_KEY, "");
        if (Config.PERSON_SINGLE_ENTERPRISE.equals(statusCode) ||
                Config.PERSON_SINGLE_INDIVIDUAL.equals(statusCode)) {
            mvProExperience.setTvMyName("项目管理");
        }else{
            mvProExperience.setTvMyName("项目经验");
        }
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.my_pro_experience_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick(R.id.mv_pro_experience)
    public void onViewClicked() {
        IntentUtil.startActivity(context, ProExperienceActivity.class);
    }
}

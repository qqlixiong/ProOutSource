package com.lixiong.straight.my.experience.viewholder;

import android.content.Context;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.activity.PersonMsgActivity;
import com.lixiong.straight.my.experience.activity.ProAchievementActivity;
import com.lixiong.straight.my.experience.activity.ProDescribeActivity;
import com.lixiong.straight.my.view.CustomMyView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by john on 2017/6/12.
 */

public class AddProExperienceHolder extends BaseHolder {
    @Bind(R.id.cmv_pro_exp_a)
    CustomMyView cmvProExpA;
    private Context context;

    public AddProExperienceHolder(Context context) {
        this.context = context;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.add_pro_experience_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick({R.id.cmv_pro_exp_a,R.id.cmv_pro_exp_c, R.id.cmv_pro_exp_d, R.id.cmv_pro_exp_e, R.id.tv_add_pro_exp_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmv_pro_exp_a:
                IntentUtil.startActivity(context, PersonMsgActivity.class);
                break;
            case R.id.cmv_pro_exp_c:
                IntentUtil.startActivity(context,ProDescribeActivity.class);
                break;
            case R.id.cmv_pro_exp_d:
                IntentUtil.startActivity(context,ProAchievementActivity.class);
                break;
            case R.id.cmv_pro_exp_e:
                break;
            case R.id.tv_add_pro_exp_save:
                break;
        }
    }
}

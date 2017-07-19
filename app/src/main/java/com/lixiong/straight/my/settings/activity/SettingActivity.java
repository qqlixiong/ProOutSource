package com.lixiong.straight.my.settings.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lixiong.straight.MainActivity;
import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.AppManger;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.DisplayUtil;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.my.bean.PersonBean;
import com.lixiong.straight.my.view.CustomMyView;

import butterknife.Bind;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.cmv_modify_mobile_num)
    CustomMyView cmvModifyMobileNum;
    @Bind(R.id.cmv_modify_pwd)
    CustomMyView cmvModifyPwd;
    @Bind(R.id.tv_exit)
    TextView tvExit;
    @Bind(R.id.cmv_clear_cache)
    CustomMyView cmvClearCache;
    @Bind(R.id.cmv_version_update)
    CustomMyView cmvVersionUpdate;
    @Bind(R.id.cmv_opinion_feedback)
    CustomMyView cmvOpinionFeedback;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initData() {
        SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(this, Config.SP);
        String userName = sharedPreferenceUtil.get(Config.USER_NAME, "");
        if (TextUtils.isEmpty(userName)) {
            ViewUtils.goneView(cmvModifyMobileNum, cmvModifyPwd, tvExit);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cmvClearCache.getLayoutParams();
            layoutParams.topMargin = DisplayUtil.dip2px(this,10);
            cmvClearCache.showMyBottomView();
            LinearLayout.LayoutParams updateLayoutParams = (LinearLayout.LayoutParams) cmvVersionUpdate.getLayoutParams();
            updateLayoutParams.topMargin = 0;
            cmvVersionUpdate.goneMyBottomView();
            LinearLayout.LayoutParams opinionFeedbackParams = (LinearLayout.LayoutParams) cmvOpinionFeedback.getLayoutParams();
            opinionFeedbackParams.topMargin = DisplayUtil.dip2px(this,10);
        }
    }

    @OnClick({R.id.cmv_set_reminders, R.id.cmv_warning_greetings, R.id.cmv_modify_mobile_num, R.id.cmv_modify_pwd, R.id.cmv_opinion_feedback, R.id.cmv_about_us, R.id.tv_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmv_set_reminders:
                IntentUtil.startActivity(this, SetRemindersActivity.class);
                break;
            case R.id.cmv_warning_greetings:
                IntentUtil.startActivity(this, WarningGreetingsActivity.class);
                break;
            case R.id.cmv_modify_mobile_num:
                IntentUtil.startActivity(this, ModifyMobileNumActivity.class);
                break;
            case R.id.cmv_modify_pwd:
                IntentUtil.startActivity(this, ModifyPwdActivity.class);
                break;
            case R.id.cmv_opinion_feedback:
                IntentUtil.startActivity(this,OpinionFeedbackActivity.class);
                break;
            case R.id.cmv_about_us:
                IntentUtil.startActivity(this,AboutUsActivity.class);
                break;
            case R.id.tv_exit:
                SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(this, Config.SP);
                sharedPreferenceUtil.clear();
                PersonBean.XmzbAccountEntityCustomBean.deleteAll(PersonBean.XmzbAccountEntityCustomBean.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean(Config.EXIT_LOGIN, true);
                IntentUtil.startActivity(this, MainActivity.class, bundle);
                AppManger.getAppManager().finishAllActivity();
                break;
        }
    }
}

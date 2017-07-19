package com.lixiong.straight.my.activity;

import com.lixiong.straight.base.BaseResumeActivity;
import com.lixiong.straight.common.utils.Config;

/**
 * 个人简介
 */
public class IndividualResumeActivity extends BaseResumeActivity{

    @Override
    protected CharSequence setTitleText() {
        return "个人简介";
    }

    @Override
    protected CharSequence setHint() {
        return "请输入您的简介";
    }

    @Override
    protected String saveKey() {
        return Config.INDIVIDUAL_RESUME;
    }
}

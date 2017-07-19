package com.lixiong.straight.my.experience.activity;

import com.lixiong.straight.base.BaseResumeActivity;
import com.lixiong.straight.common.utils.Config;

/**
 * 项目描述
 */
public class ProDescribeActivity extends BaseResumeActivity{
    @Override
    protected CharSequence setTitleText() {
        return "项目描述";
    }

    @Override
    protected CharSequence setHint() {
        return "描述该项目,向发单者展示您的项目经验\n\n例如:\n1.项目概述...\n2.人员分工...\n3.我的责任...";
    }

    @Override
    protected String saveKey() {
        return Config.PRO_DESCRIBE;
    }
}

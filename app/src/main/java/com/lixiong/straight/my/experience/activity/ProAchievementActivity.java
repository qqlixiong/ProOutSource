package com.lixiong.straight.my.experience.activity;

import com.lixiong.straight.base.BaseResumeActivity;
import com.lixiong.straight.common.utils.Config;

/**
 * 项目业绩
 * Created by john on 2017/6/13.
 */

public class ProAchievementActivity extends BaseResumeActivity{
    @Override
    protected CharSequence setTitleText() {
        return "项目业绩";
    }

    @Override
    protected CharSequence setHint() {
        return "描述您的项目业绩,向发单者展示您的工作能力\n\n例如:\n1.项目收益...\n2.我的贡献...\n3.我的收获...";
    }

    @Override
    protected String saveKey() {
        return Config.PRO_ACHIEVEMENT;
    }
}

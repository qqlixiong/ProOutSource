package com.lixiong.straight.my.follow;

import com.lixiong.straight.base.BaseScrollActivity;

/**
 * 我的关注
 */
public class MyFollowActivity extends BaseScrollActivity{

    @Override
    protected void addTitles() {
        titles.add("企业");
        titles.add("个人");
    }

    @Override
    protected void addFragment() {
        fragments.add(new EnterpriseFragment());
        fragments.add(new PersonalFragment());
    }

    @Override
    protected String getTopTitle() {
        return "我的关注";
    }

    @Override
    protected boolean isSelect() {
        return false;
    }
}

package com.lixiong.straight.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lixiong.straight.base.BaseFragment;

import java.util.List;

/**
 * Created by john on 2017/4/16.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<BaseFragment> fragments;
    private final List<String> titles;

    public ViewPagerAdapter(FragmentManager fm, List<BaseFragment> fragments,List<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    /**
     * 根据位置返回对应的Fragment
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    /**
     * 得到页面的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}

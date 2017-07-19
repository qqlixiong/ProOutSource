package com.lixiong.straight.base;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.adapter.ViewPagerAdapter;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.view.TopBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by john on 2017/6/5.
 */

public abstract class BaseScrollActivity extends AppCompatActivity implements IActivity {
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.top_my_prop)
    TopBar topMyProp;
    protected List<BaseFragment> fragments;
    protected List<String> titles;
    @Bind(R.id.activity_my_scroll)
    LinearLayout activityMyScroll;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_scroll;
    }

    @Override
    public void initData() {
        topMyProp.getTvTitle().setText(getTopTitle());
        //初始化数据
        fragments = new ArrayList<>();
        addFragment();
        titles = new ArrayList<>();
        addTitles();
        //设置ViewPager的适配器
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, titles);
        viewPager.setAdapter(adapter);
        //关联ViewPager
        tabLayout.setupWithViewPager(viewPager);
        if (isSelect()) {
            tabLayout.getTabAt(1).select();
        }
    }

    protected abstract void addTitles();

    protected abstract void addFragment();

    protected abstract String getTopTitle();

    protected abstract boolean isSelect();
}

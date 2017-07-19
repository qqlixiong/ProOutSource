package com.lixiong.straight;

import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.adapter.GuildPagerAdapter;
import com.lixiong.straight.common.utils.AppManger;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.TransparentStatusBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import cn.qqtheme.framework.util.ScreenUtils;

/**
 * 引导页
 */
public class GuideActivity extends AppCompatActivity implements IActivity, TransparentStatusBar, View.OnClickListener {

    @Bind(R.id.vp_guide)
    ViewPager vpGuide;
    @Nullable
    @Bind(R.id.tv_imm_exp)
    TextView tvImmExp;
    private List<View> images = new ArrayList<>();
    private int[] image = new int[]{R.drawable.guide_a, R.drawable.guide_b, R.drawable.guide_c, R.drawable.guide_d};
    private int currentPage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initData() {
        //将图片添加到list<view>集合中
        for (int i = 0; i < image.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(image[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            images.add(iv);
        }
        GuildPagerAdapter adapter = new GuildPagerAdapter(images);
        vpGuide.setAdapter(adapter);

        vpGuide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //currentpage后面要用
                currentPage = position;
                if (position == 3) {
                    //设置可见,如果用按钮也可以实现activity的跳转,两种跳转功能都具备
                    ViewUtils.showView(tvImmExp);
                    tvImmExp.setOnClickListener(GuideActivity.this);
                } else {
                    ViewUtils.goneView(tvImmExp);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置ViewPager的滑动监听,为了滑动到最后一页,继续滑动实现页面的跳转
        vpGuide.setOnTouchListener(new View.OnTouchListener() {
            float startX;

            float endX;


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();

                        break;
                    case MotionEvent.ACTION_UP:
                        endX = event.getX();
                        //获取屏幕的宽度
                        int width = ScreenUtils.widthPixels(getApplicationContext());
                        //根据滑动的距离来切换界面
                        LogUtil.i("当前的页面索引值为：" + currentPage);
                        if (currentPage == 3 && startX - endX >= (width / 5)) {
                            startActivity();
                        }

                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        startActivity();
    }

    private void startActivity() {
        IntentUtil.startActivity(this,MainActivity.class);
        AppManger.getAppManager().finishAllActivity();
    }
}

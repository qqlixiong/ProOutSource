package com.lixiong.straight.my.experience.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.my.experience.viewholder.ProExperienceContentHolder;
import com.lixiong.straight.my.experience.viewholder.ProExperienceHeadHolder;

import butterknife.Bind;

/**
 * 项目经验
 */
public class ProExperienceActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.fl_pro_experience_head)
    FrameLayout flProExperienceHead;
    @Bind(R.id.fl_pro_experience_content)
    FrameLayout flProExperienceContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pro_experience;
    }

    @Override
    public void initData() {
        ProExperienceHeadHolder proExperienceHeadHolder = new ProExperienceHeadHolder(this);
        flProExperienceHead.addView(proExperienceHeadHolder.getView());

        ProExperienceContentHolder proExperienceContentHolder = new ProExperienceContentHolder(this);
        flProExperienceContent.addView(proExperienceContentHolder.getView());
    }
}

package com.lixiong.straight.my.experience.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.my.experience.viewholder.AddProExperienceHolder;

import butterknife.Bind;

public class AddProExperienceActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.fl_add_pro_experience)
    FrameLayout flAddProExperience;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_pro_experience;
    }

    @Override
    public void initData() {
        AddProExperienceHolder addProExperienceHolder = new AddProExperienceHolder(this);
        flAddProExperience.addView(addProExperienceHolder.getView());
    }
}

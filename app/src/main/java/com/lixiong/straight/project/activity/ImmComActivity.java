package com.lixiong.straight.project.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.project.viewholder.ImmediateCommunicationHolder;

import butterknife.Bind;

public class ImmComActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.fl_imm_com)
    FrameLayout flImmCom;

    @Override
    public int getLayoutId() {
        return R.layout.imm_com_layout;
    }

    @Override
    public void initData() {
        ImmediateCommunicationHolder immediateCommunicationHolder = new ImmediateCommunicationHolder();
        flImmCom.addView(immediateCommunicationHolder.getView());
    }
}

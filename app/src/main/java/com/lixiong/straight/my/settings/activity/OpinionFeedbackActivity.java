package com.lixiong.straight.my.settings.activity;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.view.TopBar;

import butterknife.Bind;

/**
 * 意见与反馈
 */
public class OpinionFeedbackActivity extends AppCompatActivity implements IActivity, TextWatcher, View.OnClickListener {

    @Bind(R.id.top_opinion_feedback)
    TopBar topOpinionFeedback;
    @Bind(R.id.et_opinion_feedback)
    EditText etOpinionFeedback;
    @Bind(R.id.tv_opinion_feedback_num)
    TextView tvOpinionFeedbackNum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_opinion_feedback;
    }

    @Override
    public void initData() {
        etOpinionFeedback.addTextChangedListener(this);
        topOpinionFeedback.getTvSave().setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int length = s.length();
        if(length > 1000){
            etOpinionFeedback.setEnabled(false);
            UIUtils.toast("文本内容已经超过1000字了");
        }else {
            etOpinionFeedback.setEnabled(true);
            tvOpinionFeedbackNum.setText(String.valueOf(length));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {

    }
}

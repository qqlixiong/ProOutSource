package com.lixiong.straight.base;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.TransparentStatusBar;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by john on 2017/6/12.
 */

public abstract class BaseResumeActivity extends AppCompatActivity implements IActivity, TransparentStatusBar {
    @Bind(R.id.tv_fill_sub_num)
    TextView tvFillSubNum;
    @Bind(R.id.et_fill_sub)
    EditText etFillSub;
    @Bind(R.id.tv_individual_resume)
    TextView tvIndividualResume;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fill_substance;
    }

    @Override
    public void initData() {
        tvIndividualResume.setText(setTitleText());
        etFillSub.setHint(setHint());
        etFillSub.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length > 500) {
                    etFillSub.setEnabled(false);
                    UIUtils.toast("文本内容已经超过500字了");
                } else {
                    etFillSub.setEnabled(true);
                    tvFillSubNum.setText(String.valueOf(length));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    protected abstract CharSequence setTitleText();

    protected abstract CharSequence setHint();

    @OnClick({R.id.rl_close, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                onBackPressed();
                break;
            case R.id.tv_save:
                SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(this, Config.SP);
                sharedPreferenceUtil.set(saveKey(), etFillSub.getText().toString());
                finish();
                break;
        }
    }

    protected abstract String saveKey();
}

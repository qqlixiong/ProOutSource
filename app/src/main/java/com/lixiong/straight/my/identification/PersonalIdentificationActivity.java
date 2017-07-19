package com.lixiong.straight.my.identification;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zhouwei.library.CustomPopWindow;
import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.DisplayUtil;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.interfaces.IActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 个人认证
 */
public class PersonalIdentificationActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.tv_identification_payment_cause)
    TextView tvIdentificationPaymentCause;
    @Bind(R.id.rl_identification_good)
    RelativeLayout rlIdentificationGood;

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_identification;
    }

    @Override
    public void initData() {
        UIUtils.setUnderline(tvIdentificationPaymentCause, R.color.black);
    }

    @OnClick({R.id.tv_identification_good, R.id.tv_identification_payment_cause,R.id.tv_immediate_communication})
    public void onViewClicked(View view) {
        ImageView imageView = new ImageView(this);
        switch (view.getId()) {
            case R.id.tv_identification_good:
                imageView.setImageDrawable(UIUtils.getDrawable(R.drawable.popup_my));
                new CustomPopWindow.PopupWindowBuilder(this).setView(imageView)
                        .setFocusable(true)
                        .setOutsideTouchable(true)
                        .create()
                        .showAsDropDown(rlIdentificationGood, DisplayUtil.dip2px(this,31),
                                DisplayUtil.dip2px(this,10));
                break;
            case R.id.tv_identification_payment_cause:
                imageView.setImageDrawable(UIUtils.getDrawable(R.drawable.popup));
                CustomPopWindow popWindow = new CustomPopWindow.PopupWindowBuilder(this)
                        .setView(imageView)
                        .setFocusable(true)
                        .setOutsideTouchable(true)
                        .create();
                popWindow.showAsDropDown(tvIdentificationPaymentCause,DisplayUtil.dip2px(this,55),
                        -(popWindow.getHeight()+tvIdentificationPaymentCause.getHeight()));
                break;

            case R.id.tv_immediate_communication:
                IntentUtil.startActivity(this,ImmediateCommunicationActivity.class);
                break;
        }
    }
}

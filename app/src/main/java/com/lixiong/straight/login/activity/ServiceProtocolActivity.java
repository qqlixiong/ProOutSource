package com.lixiong.straight.login.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.TransparentStatusBar;

import butterknife.Bind;
import butterknife.OnClick;
import me.codeboy.android.aligntextview.AlignTextView;

/**
 * 服务协议
 */
public class ServiceProtocolActivity extends AppCompatActivity implements IActivity, TransparentStatusBar {

    @Nullable
    @Bind(R.id.tv_user_protocol)
    TextView tvUserProtocol;
    @Nullable
    @Bind(R.id.tv_straight_ser_pro)
    TextView tvStraightSerPro;
    @Nullable
    @Bind(R.id.atv_ser_pro_a)
    AlignTextView atvSerProA;
    @Nullable
    @Bind(R.id.tv_ser_protocol_a)
    TextView tvSerProtocolA;
    @Nullable
    @Bind(R.id.atv_ser_pro_b)
    AlignTextView atvSerProB;
    @Nullable
    @Bind(R.id.tv_ser_protocol_b)
    TextView tvSerProtocolB;
    @Nullable
    @Bind(R.id.atv_ser_pro_c)
    AlignTextView atvSerProC;
    @Nullable
    @Bind(R.id.atv_ser_pro_d)
    AlignTextView atvSerProD;
    @Nullable
    @Bind(R.id.tv_ser_protocol_c)
    TextView tvSerProtocolC;
    @Nullable
    @Bind(R.id.tv_ser_protocol_d)
    TextView tvSerProtocolD;
    @Nullable
    @Bind(R.id.atv_ser_pro_e)
    AlignTextView atvSerProE;
    @Nullable
    @Bind(R.id.tv_ser_protocol_e)
    TextView tvSerProtocolE;
    @Nullable
    @Bind(R.id.atv_ser_pro_f)
    AlignTextView atvSerProF;
    @Nullable
    @Bind(R.id.rl_protocol_content)
    RelativeLayout rlProtocolContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_protocol;
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==Config.SERVICE_PROTOCOL_WHAT){
                loadData();
            }
        }
    };

    @Override
    public void initData() {
        handler.sendEmptyMessageDelayed(Config.SERVICE_PROTOCOL_WHAT,50);
    }

    private void loadData() {
        ViewUtils.showView(rlProtocolContent);
        UIUtils.setFakeBoldText(tvUserProtocol, tvStraightSerPro, tvSerProtocolA, tvSerProtocolB,
                tvSerProtocolC, atvSerProD, tvSerProtocolD, atvSerProE, tvSerProtocolE);
        atvSerProA.setText(Constant.getAtvSerProA());
        atvSerProB.setText(Constant.getAtvSerProB());
        atvSerProC.setText(Constant.getAtvSerProC());
        atvSerProD.setText(Constant.getAtvSerProD());
        atvSerProE.setText(Constant.getAtvSerProE());
        atvSerProF.setText(Constant.getAtvSerProF());
    }

    @OnClick(R.id.fl_ser_protocol_back)
    public void onViewClicked() {
        onBackPressed();
    }
}

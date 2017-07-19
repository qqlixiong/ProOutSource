package com.lixiong.straight.my.viewholder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.GlideImage;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.login.activity.LoginActivity;
import com.lixiong.straight.login.activity.ReceiptOrdersActivity;
import com.lixiong.straight.my.activity.PersonMsgActivity;
import com.lixiong.straight.my.bean.PersonBean;
import com.lixiong.straight.my.settings.activity.SettingActivity;
import com.orm.SugarRecord;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 用户信息
 * Created by john on 2017/5/20.
 */

public class MyHeadHolder extends BaseHolder {
    @Nullable
    @Bind(R.id.iv_person)
    CircleImageView ivPerson;
    @Nullable
    @Bind(R.id.tv_please_login)
    TextView tvPleaseLogin;
    @Nullable
    @Bind(R.id.tv_person_name)
    TextView tvPersonName;
    @Nullable
    @Bind(R.id.tv_attestation)
    TextView tvAttestation;
    @Nullable
    @Bind(R.id.iv_issuance)
    ImageView ivIssuance;
    @Nullable
    @Bind(R.id.rl_per_msg)
    RelativeLayout rlPerMsg;
    private Context context;
    //状态码
    private String statusCode;

    public MyHeadHolder(Context context) {
        this.context = context;
        SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(context, Config.SP);
        statusCode = sharedPreferenceUtil.get(Config.STATUS_CODE_KEY,"");
        if(Config.PERSON_SINGLE_ENTERPRISE.equals(statusCode)||
                Config.PERSON_SINGLE_INDIVIDUAL.equals(statusCode)){
            ViewUtils.setImageView(ivIssuance,R.string.issuance,R.string.orders,R.drawable.icon_btn_those_orders);
        }else if(Config.ORDERS_SINGLE_ENTERPRISE.equals(statusCode)||
                Config.ORDERS_SINGLE_INDIVIDUAL.equals(statusCode)){
            ViewUtils.setImageView(ivIssuance,R.string.orders,R.string.issuance,R.drawable.icon_btn_issuance);
        }
        String userName = sharedPreferenceUtil.get(Config.USER_NAME, "");
        if (!TextUtils.isEmpty(userName)) {
            ViewUtils.hideView(tvPleaseLogin);
            ViewUtils.showView(tvPersonName, tvAttestation, rlPerMsg, ivIssuance);
//            List<PersonBean.XmzbAccountEntityCustomBean> personBeen =
//                    SugarRecord.listAll(PersonBean.XmzbAccountEntityCustomBean.class);
//            LogUtil.i("账号密码登录  保存的我的个人信息:" + personBeen.toString());
            List<PersonBean.XmzbAccountEntityCustomBean> personBeen = Constant.getUserMessage();
            if (personBeen.size() > 0) {
                String personName = personBeen.get(0).getPersonName();
                String personLogo = personBeen.get(0).getPersonLogo();
                LogUtil.i("用户LOGO:"+personLogo);
                if (!TextUtils.isEmpty(personName)) {
                    tvPersonName.setText(personName);
                }
                if(!TextUtils.isEmpty(personLogo)){
                    GlideImage.getInstance().displayImage(context,personLogo,R.drawable.my_icon,ivPerson);
                }
            }
        }
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.my_head_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick({R.id.tv_please_login, R.id.rl_setting, R.id.rl_per_msg,R.id.iv_issuance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_please_login:
                IntentUtil.startActivity(context, LoginActivity.class);
                break;

            case R.id.rl_per_msg:
                IntentUtil.startActivity(context, PersonMsgActivity.class);
                break;

            case R.id.rl_setting:
                IntentUtil.startActivity(context, SettingActivity.class);
                break;

            case R.id.iv_issuance:
                Bundle bundle = new Bundle();
                if(ViewUtils.isChange(view,R.string.issuance)){
                    bundle.putString(Config.BILL, Config.PERSON_SINGLE);
                }else if (ViewUtils.isChange(view,R.string.orders)){
                    bundle.putString(Config.BILL, Config.ORDERS_SINGLE);
                }
                IntentUtil.startActivity(context, ReceiptOrdersActivity.class,bundle);
                break;
        }
    }
}

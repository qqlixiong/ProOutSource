package com.lixiong.straight.my.settings.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.viewholder.ConfirmBtnHolder;
import com.lixiong.straight.login.viewholder.GetCodeHolder;
import com.lixiong.straight.my.bean.PersonBean;
import com.lixiong.straight.my.settings.view.ModifyView;

import java.util.List;

import butterknife.Bind;

/**
 * 修改手机号
 */
public class ModifyMobileNumActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.fl_modify_code)
    FrameLayout flModifyCode;
    @Nullable
    @Bind(R.id.modify_view_phone)
    ModifyView modifyViewPhone;
    @Bind(R.id.fl_modify_phone_confirm)
    FrameLayout flModifyPhoneConfirm;
    @Nullable
    @Bind(R.id.modify_view_edit_phone)
    ModifyView modifyViewEditPhone;          //输入的手机号
    private GetCodeHolder getCodeHolder;
    private String phoneNum;
    private boolean isModifyPhone;
    private EditText inputET;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_mobile_num;
    }

    @Override
    public void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isModifyPhone = bundle.getBoolean(Config.IS_MODIFY, false);
            if (isModifyPhone) {
                ViewUtils.goneView(modifyViewPhone);
                ViewUtils.showView(modifyViewEditPhone);
                inputET = modifyViewEditPhone.getEtText();
            }
        }

        getCodeHolder = new GetCodeHolder(isModifyPhone,UIUtils.getDrawable(R.drawable.icon_img_iphone));
        flModifyCode.addView(getCodeHolder.getView());
        if(isModifyPhone){
            getCodeHolder.setET(inputET);
        }else {
            List<PersonBean.XmzbAccountEntityCustomBean> personMessage = /*PersonBean.XmzbAccountEntityCustomBean.
                    listAll(PersonBean.XmzbAccountEntityCustomBean.class)*/Constant.getUserMessage();
            if (personMessage.size() > 0) {
                phoneNum = String.valueOf(personMessage.get(0).getCellphone());
                modifyViewPhone.setTextPhone(phoneNum);
                getCodeHolder.setPhoneNumber(phoneNum);
            }
        }

        ConfirmBtnHolder confirmBtnHolder = new ConfirmBtnHolder(this);
        if(isModifyPhone){
            confirmBtnHolder.setEtPhone(inputET);
        }else {
            confirmBtnHolder.setPhoneNumber(phoneNum);
        }
        confirmBtnHolder.setModify(true);
        confirmBtnHolder.setTextBtn(isModifyPhone ? UIUtils.getString(R.string.modify_confirm) : UIUtils.getString(R.string.modify_next));
        confirmBtnHolder.setGetCodeHolder(getCodeHolder);
        List<PersonBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBeanList =
                /*PersonBean.XmzbAccountEntityCustomBean.listAll(PersonBean.XmzbAccountEntityCustomBean.class)*/Constant.getUserMessage();
        if(xmzbAccountEntityCustomBeanList.size()>0){
            confirmBtnHolder.setData(xmzbAccountEntityCustomBeanList.get(0));
        }
        flModifyPhoneConfirm.addView(confirmBtnHolder.getView());
    }
}

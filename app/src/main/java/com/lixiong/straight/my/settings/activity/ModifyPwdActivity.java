package com.lixiong.straight.my.settings.activity;

import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.login.viewholder.ConfirmBtnHolder;
import com.lixiong.straight.login.viewholder.GetCodeHolder;
import com.lixiong.straight.my.bean.PersonBean;
import com.lixiong.straight.my.settings.view.ModifyView;

import java.util.List;

import butterknife.Bind;

/**
 * 修改密码
 */
public class ModifyPwdActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.fl_modify_pwd_code)
    FrameLayout flModifyPwdCode;
    @Bind(R.id.mv_pwd_phone)
    ModifyView mvPwdPhone;
    @Bind(R.id.mv_pwd_new)
    ModifyView mvPwdNew;
    @Bind(R.id.fl_modify_pwd_confirm)
    FrameLayout flModifyPwdConfirm;
    private GetCodeHolder getCodeHolder;
    private String phoneNum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public void initData() {
        getCodeHolder = new GetCodeHolder(false, UIUtils.getDrawable(R.drawable.phone));
        flModifyPwdCode.addView(getCodeHolder.getView());

        List<PersonBean.XmzbAccountEntityCustomBean> personMessage = /*PersonBean.XmzbAccountEntityCustomBean.
                listAll(PersonBean.XmzbAccountEntityCustomBean.class)*/Constant.getUserMessage();
        if (personMessage.size() > 0) {
            phoneNum = String.valueOf(personMessage.get(0).getCellphone());
            mvPwdPhone.setTextPhone(phoneNum);
            getCodeHolder.setPhoneNumber(phoneNum);
        }

        ConfirmBtnHolder confirmBtnHolder = new ConfirmBtnHolder(this);
        confirmBtnHolder.setPhoneNumber(phoneNum);
        confirmBtnHolder.setEtPwdNew(mvPwdNew.getEtText());
        confirmBtnHolder.setModify(true);
        confirmBtnHolder.setTextBtn(UIUtils.getString(R.string.modify_pwd_confirm));
        confirmBtnHolder.setGetCodeHolder(getCodeHolder);
        List<PersonBean.XmzbAccountEntityCustomBean> xmzbAccountEntityCustomBeanList =
                /*PersonBean.XmzbAccountEntityCustomBean.listAll(PersonBean.XmzbAccountEntityCustomBean.class)*/Constant.getUserMessage();
        if(xmzbAccountEntityCustomBeanList.size()>0){
            confirmBtnHolder.setData(xmzbAccountEntityCustomBeanList.get(0));
        }
        flModifyPwdConfirm.addView(confirmBtnHolder.getView());
    }
}

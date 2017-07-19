package com.lixiong.straight.my.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseUploadImageActivity;
import com.lixiong.straight.common.AddressPickTask;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.my.bean.PersonBean;
import com.lixiong.straight.my.view.CustomMyView;
import com.lixiong.straight.view.TopBar;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import cn.qqtheme.framework.entity.City;
import cn.qqtheme.framework.entity.County;
import cn.qqtheme.framework.entity.Province;
import okhttp3.Call;

public class PersonMsgActivity extends BaseUploadImageActivity{

    @Bind(R.id.cmv_name)
    CustomMyView cmvName;
    @Bind(R.id.cmv_phone)
    CustomMyView cmvPhone;
    @Bind(R.id.cmv_we_chat)
    CustomMyView cmvWeChat;
    @Bind(R.id.cmv_email)
    CustomMyView cmvEmail;
    @Bind(R.id.cmv_head)
    CustomMyView cmvHead;
    @Bind(R.id.cmv_location)
    CustomMyView cmvLocation;
    @Bind(R.id.cmv_service_category)
    CustomMyView cmvServiceCategory;
    @Bind(R.id.cmv_individual_resume)
    CustomMyView cmvIndividualResume;
    @Bind(R.id.top_person_msg)
    TopBar topPersonMsg;
    private LoadingDialog loadingDialog;
    private String selectProvince;
    private String selectCity;
    private SharedPreferenceUtil sharedPreferenceUtil;

    @Override
    public int getLayoutId() {
        return R.layout.activity_person_msg;
    }

    @Override
    public void initData() {
        sharedPreferenceUtil = new SharedPreferenceUtil(this,Config.SP);
        cmvIndividualResume.getIvMy().setImageResource(R.drawable.icon);
        List<PersonBean.XmzbAccountEntityCustomBean> personMessage = /*PersonBean.XmzbAccountEntityCustomBean.
                listAll(PersonBean.XmzbAccountEntityCustomBean.class)*/Constant.getUserMessage();
        if (personMessage.size() > 0) {
            PersonBean.XmzbAccountEntityCustomBean personMsg = personMessage.get(0);
            cmvName.getEtMyResume().setText(personMsg.getPersonName());
            cmvPhone.getEtMyResume().setText(personMsg.getCellphone());
            cmvWeChat.getEtMyResume().setText(personMsg.getWeixinnum());
            cmvEmail.getEtMyResume().setText(personMsg.getEmail());
        }

        topPersonMsg.getRlSave().setOnClickListener(new SaveListener());
    }

    private class SaveListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
                multiFileUpload();
        }
    }

    private void multiFileUpload() {
        loadingDialog = new LoadingDialog(this);
        List<PersonBean.XmzbAccountEntityCustomBean> personMsgList = /*PersonBean.XmzbAccountEntityCustomBean.listAll(
                PersonBean.XmzbAccountEntityCustomBean.class)*/Constant.getUserMessage();
        String personCode = "";
        if(personMsgList.size()>0){
            personCode = personMsgList.get(0).getPersonCode();
        }
        Map<String,String> map = new HashMap<>();
        map.put("uploadIdentification","logo");
        if(!TextUtils.isEmpty(personCode)){
            map.put("personCode",personCode);
        }
        String personLogo = getPicStr();
        LogUtil.i("图片的BASE64码："+personLogo);
        if(!TextUtils.isEmpty(personLogo)){
            map.put("personLogo",personLogo);
        }
        String nameStr = cmvName.getEtMyResume().getText().toString();
        if(!TextUtils.isEmpty(nameStr)){
            map.put("personName",nameStr);
        }
        String phoneStr = cmvPhone.getEtMyResume().getText().toString();
        if(!TextUtils.isEmpty(phoneStr)){
            map.put("cellphone",phoneStr);
        }
        String weiChatStr = cmvWeChat.getEtMyResume().getText().toString();
        if(!TextUtils.isEmpty(weiChatStr)){
            map.put("weixinnum",weiChatStr);
        }
        String emailStr = cmvEmail.getEtMyResume().getText().toString();
        if(!TextUtils.isEmpty(emailStr)){
            map.put("email",emailStr);
        }
        if(!TextUtils.isEmpty(selectProvince)){
            map.put("province",selectProvince);
        }
        if(!TextUtils.isEmpty(selectCity)){
            map.put("city",selectCity);
        }
        String personIndividualStr = sharedPreferenceUtil.get(Config.INDIVIDUAL_RESUME,"");
        if(!TextUtils.isEmpty(personIndividualStr)){
            map.put("personIntroduct",personIndividualStr);
        }
        String serviceCategoryOne = sharedPreferenceUtil.get(Config.SERVICE_CATEGORY_ONE_STRINGS,"");
        String serviceCategoryTow = sharedPreferenceUtil.get(Config.SERVICE_CATEGORY_TOW_STRINGS,"");
        LogUtil.e("一级服务类目："+serviceCategoryOne+"二级服务类目："+serviceCategoryTow);
        if(TextUtils.isEmpty(serviceCategoryOne) && TextUtils.isEmpty(serviceCategoryTow)){
            UIUtils.toast("请选择服务类目");
            loadingDialog.dismiss();
            return;
        }else {
            map.put("itemCodefirst",serviceCategoryOne);
            map.put("itemCodesecond",serviceCategoryTow);
        }
        OkHttpUtils.post().url(URLParam.UPLOAD_PERSONAL_MESSAGE_URL)
                .params(map)
                .build()
                .execute(new MyStringCallback());

    }

    @OnClick({R.id.cmv_head, R.id.cmv_location, R.id.cmv_service_category, R.id.cmv_individual_resume})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmv_head:
                showSculptureDialog();
                break;
            case R.id.cmv_location:
                AddressPickTask task = new AddressPickTask(this);
                task.setHideCounty(true);
                task.setCallback(new AddressPickTask.Callback() {
                    @Override
                    public void onAddressInitFailed() {
                        UIUtils.toast("数据初始化失败");
                    }

                    @Override
                    public void onAddressPicked(Province province, City city, County county) {
                        selectProvince = province.getAreaName();
                        selectCity =  city.getAreaName();
                        cmvLocation.getTvMyResume().setText(selectProvince);
                    }
                });
                task.execute("广东", "深圳");
                break;
            case R.id.cmv_service_category:
                IntentUtil.startActivity(this, ServiceCategoryActivity.class);
                break;
            case R.id.cmv_individual_resume:
                IntentUtil.startActivity(this, IndividualResumeActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Config.TAKE_PHOTO_REQUEST_TWO:
                takePhotoResult(resultCode, this, data, cmvHead.getIvMyPic());
                break;

            case Config.PICK_IMAGE_FROM_ALBUM:
                pickImageFromAlbumResult(resultCode, this, data, cmvHead.getIvMyPic());
                break;
        }
    }

    private class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            UIUtils.toast("上传失败 ");
            LogUtil.e("上传失败 Exception e:"+e);
            loadingDialog.dismiss();
        }

        @Override
        public void onResponse(String response, int id) {
            UIUtils.toast("上传成功");
            LogUtil.i("上传个人信息的返回："+response);
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

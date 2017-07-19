package com.lixiong.straight.my.identification;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseUploadImageActivity;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.my.bean.PersonBean;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 提交认证材料
 */
public class ImmediateCommunicationActivity extends BaseUploadImageActivity implements View.OnClickListener {

    @Nullable
    @Bind(R.id.iv_no_photo)
    ImageView ivNoPhoto;
    @Nullable
    @Bind(R.id.tv_no_photo)
    TextView tvNoPhoto;
    @Nullable
    @Bind(R.id.tv_imm_comm_c)
    TextView tvImmCommC;
    @Nullable
    @Bind(R.id.iv_imm_com_a)
    ImageView ivImmComA;
    @Nullable
    @Bind(R.id.tv_imm_comm_e)
    TextView tvImmCommE;
    @Nullable
    @Bind(R.id.tv_imm_comm_d)
    TextView tvImmCommD;
    @Bind(R.id.iv_no_photo_a)
    ImageView ivNoPhotoA;
    @Bind(R.id.tv_imm_comm_a)
    TextView tvImmCommA;
    @Bind(R.id.tv_imm_comm_b)
    TextView tvImmCommB;
    @Bind(R.id.iv_imm_comm_picture)
    ImageView ivImmCommPicture;

    @Override
    public int getLayoutId() {
        return R.layout.activity_immediate_communication;
    }

    @Override
    public void initData() {
        List<PersonBean.XmzbAccountEntityCustomBean> personBeanS = /*PersonBean.XmzbAccountEntityCustomBean
                .listAll(PersonBean.XmzbAccountEntityCustomBean.class)*/Constant.getUserMessage();
        if(personBeanS.size()>0){
            PersonBean.XmzbAccountEntityCustomBean person = personBeanS.get(0);
            String personName = person.getPersonName();
        }
    }

    @OnClick({R.id.button_imm_comm, R.id.button_upload_picture})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_imm_comm:
                break;
            case R.id.button_upload_picture:
                showSculptureDialog();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Config.TAKE_PHOTO_REQUEST_TWO:
                takePhotoResult(resultCode, this, data, ivImmCommPicture);
                break;

            case Config.PICK_IMAGE_FROM_ALBUM:
                pickImageFromAlbumResult(resultCode, this, data, ivImmCommPicture);
                break;
        }
    }
}

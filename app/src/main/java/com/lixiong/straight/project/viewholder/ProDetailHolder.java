package com.lixiong.straight.project.viewholder;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseGlideHolder;
import com.lixiong.straight.common.GlideImage;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.project.bean.ProDetail;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.codeboy.android.aligntextview.AlignTextView;

/**
 * Created by john on 2017/5/22.
 */

public class ProDetailHolder extends BaseGlideHolder<ProDetail.ProjectsDetailEntity> {
    @Bind(R.id.tv_invite_city)
    TextView tvInviteCity;               //深圳
    @Bind(R.id.tv_invite_person_num)
    TextView tvInvitePersonNum;          //1人
    @Bind(R.id.tv_pro_period)
    TextView tvProPeriod;               //10天
    @Bind(R.id.tv_price_range)
    TextView tvPriceRange;              //2W-3W
    @Bind(R.id.tv_pro_name)
    TextView tvProName;                 //企业LOGO设计
    @Bind(R.id.tv_demand_category)
    TextView tvDemandCategory;          //品牌设计
    @Bind(R.id.tv_demand_describe)
    AlignTextView tvDemandDescribe;      //需求描述
    @Nullable
    @Bind(R.id.tv_skill_tag_a)
    TextView tvSkillTagA;
    @Nullable
    @Bind(R.id.tv_skill_tag_b)
    TextView tvSkillTagB;
    @Nullable
    @Bind(R.id.tv_skill_tag_c)
    TextView tvSkillTagC;
    @Bind(R.id.civ_personal)
    CircleImageView civPersonal;        //个人图片
    @Bind(R.id.tv_pro_person_name)
    TextView tvProPersonName;            //个人名字
    @Bind(R.id.tv_pro_person_ser_type)
    TextView tvProPersonSerType;         //个人  品牌设计
    @Bind(R.id.iv_follow)
    ImageButton ivFollow;                   //关注按钮
    @Bind(R.id.tv_person_describe)
    AlignTextView tvPersonDescribe;            //个人简介

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.pro_detail_layout);
    }

    @Override
    protected void refreshView(ProDetail.ProjectsDetailEntity data) {
        tvInviteCity.setText(data.getCity());
        tvInvitePersonNum.setText(data.getNumber());
        tvProPeriod.setText(data.getProtime());
        tvPriceRange.setText(data.getPriceBegin() + "-" + data.getPriceEnd());
        tvProName.setText(data.getProname());
        tvDemandCategory.setText(data.getProItemFirstNames());
        tvDemandDescribe.setText(data.getDescription());
        String skillS = data.getItemName();
        String[] skillList = skillS.split(",");
        for (int i = 0; i < skillList.length; i++) {
            if (i == 0) {
                ViewUtils.showView(tvSkillTagA);
                tvSkillTagA.setText(skillList[i]);
            }
            if (i == 1) {
                ViewUtils.showView(tvSkillTagB);
                tvSkillTagB.setText(skillList[i]);
            }
            if (i == 2) {
                ViewUtils.showView(tvSkillTagC);
                tvSkillTagC.setText(skillList[i]);
            }
        }
        tvProPersonName.setText(data.getPersonName());
        tvProPersonSerType.setText(data.getPerItemFirstNames());
        tvPersonDescribe.setText(data.getPersonIntroduct());
        String attendStatus = data.getAttendStatus();
        if(TextUtils.equals(attendStatus,"0")){
            ivFollow.setImageDrawable(UIUtils.getDrawable(R.drawable.follow));
            ivFollow.setEnabled(true);
        }else if(TextUtils.equals(attendStatus,"1")){
            ivFollow.setImageDrawable(UIUtils.getDrawable(R.drawable.concern));
            ivFollow.setEnabled(false);
        }
    }

    @Override
    public void initGlideImage(Context context, ProDetail.ProjectsDetailEntity data, GlideImage glideImage) {
        glideImage.displayImage(context, data.getPersonLogo(), R.drawable.my_a, civPersonal);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @OnClick({R.id.iv_task_require_down, R.id.iv_follow, R.id.iv_person_task_require_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_task_require_down:
                int maxLines = tvDemandDescribe.getMaxLines();
                if(maxLines == 3){
                    tvDemandDescribe.setMaxLines(Integer.MAX_VALUE);
                }else {
                    tvDemandDescribe.setMaxLines(3);
                }
                break;
            case R.id.iv_follow:
                break;
            case R.id.iv_person_task_require_down:
                int personMaxLines = tvPersonDescribe.getMaxLines();
                if(personMaxLines == 3){
                    tvPersonDescribe.setMaxLines(Integer.MAX_VALUE);
                }else {
                    tvPersonDescribe.setMaxLines(3);
                }
                break;
        }
    }
}

package com.lixiong.straight.project.holder;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.adapter.ModelRecyclerAdapter;
import com.lixiong.straight.adapter.RecyclerItemViewId;
import com.lixiong.straight.project.bean.ProMsg;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.project.activity.ProDetailActivity;
import com.lixiong.straight.view.CornerFlagView;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by john on 2017/5/21.
 */
@RecyclerItemViewId(R.layout.pro_list_item)
public class ProRVHolder extends ModelRecyclerAdapter.ModelViewHolder {
    @Bind(R.id.tv_pro_title)
    TextView tvProTitle;              //电商产品页面设计
    @Nullable
    @Bind(R.id.cornerFlagView)
    CornerFlagView cornerFlagView;
    @Bind(R.id.tv_pro_city)
    TextView tvProCity;              //深圳市
    @Bind(R.id.tv_time)
    TextView tvTime;                 //5天
    @Bind(R.id.tv_pro_price)
    TextView tvProPrice;            //2W-3W
    @Bind(R.id.tv_ser_cate)
    TextView tvSerCate;             //电商服务
    @Bind(R.id.tv_pro_cate_a)
    TextView tvProCateA;            //跨境电商
    @Nullable
    @Bind(R.id.tv_pro_cate_b)
    TextView tvProCateB;           //页面设计
    @Nullable
    @Bind(R.id.tv_pro_cate_c)
    TextView tvProCateC;          //H5设计
    @Bind(R.id.iv_pro_person)
    CircleImageView ivProPerson;
    @Bind(R.id.tv_pro_name)
    TextView tvProName;           //王宽英
    @Bind(R.id.iv_person_type)
    ImageView ivPersonType;
    private Context context;
    private ProMsg.ProjectsEntity projectMsg;   //项目信息

    public ProRVHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void convert(Object item, ModelRecyclerAdapter adapter, Context context, int position) {
        this.context = context;
        projectMsg = (ProMsg.ProjectsEntity) item;
        if(position<=2){
            ViewUtils.showView(cornerFlagView);
        }
        tvProTitle.setText(projectMsg.getProname());
        tvProCity.setText(projectMsg.getCity());
        tvTime.setText(projectMsg.getProtime());
        tvProPrice.setText(projectMsg.getPriceBegin()+"-"+projectMsg.getPriceEnd());
        String itemNameList = projectMsg.getItemName();
        String[] itemName = itemNameList.split(",");
        for(int i=0;i<itemName.length;i++){
            if(i==0){
                tvProCateA.setText(itemName[i]);
            }
            if(i==1){
                ViewUtils.showView(tvProCateB);
                tvProCateB.setText(itemName[i]);
            }
            if(i==2){
                ViewUtils.showView(tvProCateC);
                tvProCateC.setText(itemName[i]);
            }
        }
        String identityTypeId = projectMsg.getIdentityTypeid();
        if(TextUtils.equals(identityTypeId, Config.PERSON_SINGLE_ENTERPRISE) ||
                TextUtils.equals(identityTypeId, Config.ORDERS_SINGLE_ENTERPRISE)){
            ivPersonType.setImageDrawable(UIUtils.getDrawable(R.drawable.enterprise));
        }else if(TextUtils.equals(identityTypeId, Config.PERSON_SINGLE_INDIVIDUAL) ||
                TextUtils.equals(identityTypeId, Config.ORDERS_SINGLE_INDIVIDUAL)){
            ivPersonType.setImageDrawable(UIUtils.getDrawable(R.drawable.personal));
        }
        glideImage.displayImage(context,projectMsg.getPersonLogo(),R.drawable.my_a,ivProPerson);
        tvProName.setText(projectMsg.getPersonName());
        tvSerCate.setText(projectMsg.getProItemFirstNames());
    }

    @OnClick(R.id.rl_project)
    public void onViewClicked() {
        String accSysCode = projectMsg.getAccSyscode();
        Bundle bundle = new Bundle();
        bundle.putString(Config.ACC_SYS_CODE,accSysCode);
        IntentUtil.startActivity2(context, ProDetailActivity.class,bundle);
    }
}

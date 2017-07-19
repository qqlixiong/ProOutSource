package com.lixiong.straight.project.viewholder;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.zhouwei.library.CustomPopWindow;
import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseCategoryHolder;
import com.lixiong.straight.common.Category;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.URLParam;
import com.lixiong.straight.login.dialog.LoadingDialog;
import com.lixiong.straight.my.bean.ServiceCategory;
import com.lixiong.straight.project.bean.ProMsg;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by john on 2017/5/21.
 */

public class ProHeadHolder extends BaseCategoryHolder {
    @Bind(R.id.pro_head_line_a)
    View proHeadLineA;
    private Context context;
    private CustomPopWindow customPopWindowHot;
    private CustomPopWindow customPWCategory;
    private CustomPopWindow customPWPrice;
    private RadioGroup rgCategory;
    private TagFlowLayout cateFlowLayout;
    private TagFlowLayout priceFlowLayout;
    private SharedPreferenceUtil sharedPreferenceUtil;
    private LoadingDialog loadingDialog;
    private Handler handler;

    public ProHeadHolder(Context context,Handler handler) {
        super();
        this.context = context;
        this.handler = handler;
        sharedPreferenceUtil = new SharedPreferenceUtil(context, Config.SP);
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.pro_head_layout_b);
    }

    @Override
    protected void refreshView(Object data) {

    }

    @OnClick({R.id.cmv_the_hottest, R.id.cmv_category, R.id.cmv_quoted_price})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cmv_the_hottest:
                View contentView = LayoutInflater.from(context).inflate(R.layout.hot_pw_layout, null);
                //处理popWindow 显示内容
                handleHottest(contentView);
                customPopWindowHot = new CustomPopWindow.PopupWindowBuilder(context)
                        .size(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
                        .setView(contentView)
                        .create().showAsDropDown(proHeadLineA, 0, 0);
                break;
            case R.id.cmv_category:
                View contentViewB = LayoutInflater.from(context).inflate(R.layout.category_pw_layout, null);
                rgCategory = (RadioGroup) contentViewB.findViewById(R.id.rg_pw_category);
                cateFlowLayout = (TagFlowLayout) contentViewB.findViewById(R.id.tfl_pw_category);
                Category.getInstance().createRadioButton(serviceCategoryOneList,rgCategory,R.drawable.category_backgound_selector,
                        R.drawable.service_category_text_color_drawable_selector,new RgCheckedChangeListener());
                createTagFlowLayout(list1,0);
                customPWCategory = new CustomPopWindow.PopupWindowBuilder(context)
                        .setView(contentViewB)
                        .size(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
                        .create().showAsDropDown(proHeadLineA, 0, 0);
                break;
            case R.id.cmv_quoted_price:
                View contentViewC = LayoutInflater.from(context).inflate(R.layout.price_pw_layout, null);
                priceFlowLayout = (TagFlowLayout) contentViewC.findViewById(R.id.tfl_pw_price);
                handlePrice(contentViewC);
                customPWPrice = new CustomPopWindow.PopupWindowBuilder(context)
                        .setView(contentViewC)
                        .size(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT)
                        .create().showAsDropDown(proHeadLineA, 0, 0);
                break;
        }
    }

    private void handlePrice(View contentView) {
        final int currentPriceIndex = sharedPreferenceUtil.get(Config.PRICE_INDEX,0);
        String[] priceRegion = getPriceArray();   //价格区间
        priceFlowLayout.setAdapter(new TagAdapter<String>(priceRegion) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) UIUtils.inflate(R.layout.price_cate_item, priceFlowLayout, false);
                textView.setText(s);
                if(currentPriceIndex == position){
                    textView.setBackgroundResource(R.drawable.prop_btn_bg);
                    textView.setTextColor(UIUtils.getColorStateList(R.color.white));
                }else {
                    textView.setBackgroundResource(R.drawable.ser_cate_normal_bg);
                    textView.setTextColor(UIUtils.getColorStateList(R.color.color_service_protocol_text_a));
                }
                return textView;
            }
        });

        priceFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                for(int i=0;i<parent.getChildCount();i++){
                    View child = parent.getChildAt(i);
                    TextView textView = (TextView) child.findViewById(R.id.tv_price_cate_x);
                    if(i==position){
                        textView.setBackgroundResource(R.drawable.prop_btn_bg);
                        textView.setTextColor(UIUtils.getColorStateList(R.color.white));
                        sharedPreferenceUtil.set(Config.PRICE_INDEX,position);
                    }else {
                        textView.setBackgroundResource(R.drawable.ser_cate_normal_bg);
                        textView.setTextColor(UIUtils.getColorStateList(R.color.color_service_protocol_text_a));
                    }
                }
                return true;
            }
        });

        priceFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if(customPWPrice != null){
                    customPWPrice.dissmiss();
                }
                for(Integer index : selectPosSet){
                    Map<String, String> priceMap = getStringPriceMap(index);
                    okHttpRequestCate(priceMap);
                }
            }
        });

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(customPWPrice != null){
                    customPWPrice.dissmiss();
                }
            }
        };
        contentView.findViewById(R.id.view_b).setOnClickListener(listener);
    }

    @NonNull
    private Map<String, String> getStringPriceMap(int position) {
        Map<String,String> priceMap = new HashMap<String, String>();
        String priceBegin = "";
        String priceEnd = "";
        switch (position){
            case 0:
                break;

            case 1:
                priceBegin = "50";
                priceEnd = "1000";
                break;

            case 2:
                priceBegin = "1000";
                priceEnd = "5000";
                break;

            case 3:
                priceBegin = "5000";
                priceEnd = "10000";
                break;

            case 4:
                priceBegin = "10000";
                priceEnd = "20000";
                break;

            case 5:
                priceBegin = "20000";
                priceEnd = "50000";
                break;

            case 6:
                priceBegin = "50000";
                priceEnd = "100000";
                break;

            case 7:
                priceBegin = "100000";
                break;
        }
        if(!TextUtils.isEmpty(priceBegin)){
            priceMap.put("priceBegin",priceBegin);
        }
        if(!TextUtils.isEmpty(priceEnd)){
            priceMap.put("priceEnd",priceEnd);
        }
        return priceMap;
    }

    private void handleHottest(View contentView) {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customPopWindowHot != null) {
                    customPopWindowHot.dissmiss();
                }
                switch (v.getId()) {
                    case R.id.rl_hot:
                        break;
                    case R.id.rl_new:
                        break;
                }
            }
        };
        contentView.findViewById(R.id.rl_hot).setOnClickListener(listener);
        contentView.findViewById(R.id.rl_new).setOnClickListener(listener);
        contentView.findViewById(R.id.view_a).setOnClickListener(listener);
    }

    @Override
    protected void createTagFlowLayout(final List<ServiceCategory> categoryList, final int checkedId) {
        if(cateFlowLayout != null){
            final int currentCategoryIndex = sharedPreferenceUtil.get(Config.CATEGORY_INDEX,-1);
            final int currentCheckId = sharedPreferenceUtil.get(Config.CATEGORY_CHECKED_ID,-1);
            cateFlowLayout.setAdapter(new TagAdapter<ServiceCategory>(categoryList) {
                @Override
                public View getView(FlowLayout parent, int position, ServiceCategory serviceCategory) {
                    View view = UIUtils.inflate(R.layout.cate_item, cateFlowLayout, false);
                    TextView tvCategory = (TextView) view.findViewById(R.id.tv_category_z);
                    ImageView ivCategory = (ImageView) view.findViewById(R.id.iv_category_z);
                    tvCategory.setText(serviceCategory.getItemName());
                    if(currentCategoryIndex != -1 && currentCheckId != -1 &&
                            currentCategoryIndex == position && currentCheckId == checkedId){
                        tvCategory.setTextColor(UIUtils.getColorStateList(R.color.color_status_bar));
                        ivCategory.setImageDrawable(UIUtils.getDrawable(R.drawable.button));
                    }else {
                        tvCategory.setTextColor(UIUtils.getColorStateList(R.color.color_service_cate));
                        ivCategory.setImageDrawable(UIUtils.getDrawable(R.drawable.button_icon));
                    }
                    return view;
                }
            });

            cateFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    for(int i=0;i<parent.getChildCount();i++){
                        View child = parent.getChildAt(i);
                        TextView tvCategory = (TextView) child.findViewById(R.id.tv_category_z);
                        ImageView ivCategory = (ImageView) child.findViewById(R.id.iv_category_z);
                        if(i==position){
                            tvCategory.setTextColor(UIUtils.getColorStateList(R.color.color_status_bar));
                            ivCategory.setImageDrawable(UIUtils.getDrawable(R.drawable.button));
                            sharedPreferenceUtil.set(Config.CATEGORY_INDEX,position);
                            sharedPreferenceUtil.set(Config.CATEGORY_CHECKED_ID,checkedId);
                        }else {
                            tvCategory.setTextColor(UIUtils.getColorStateList(R.color.color_service_cate));
                            ivCategory.setImageDrawable(UIUtils.getDrawable(R.drawable.button_icon));
                        }
                    }
                    return true;
                }
            });

            cateFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    if(customPWCategory != null){
                        customPWCategory.dissmiss();
                    }
                    for(int index : selectPosSet){
                        Map<String,String> map = new HashMap<String, String>();
                        map.put("itemCodefirst",serviceCategoryOneList.get(checkedId).getItemCode());
                        map.put("itemCode",categoryList.get(index).getItemCode());
                        okHttpRequestCate(map);
                    }
                }
            });
        }
    }

    /**
     * 根据条件查询结果
     * @param map   参数
     */
    private void okHttpRequestCate(Map<String, String> map) {
        loadingDialog = new LoadingDialog(context);
        OkHttpUtils.get().url(URLParam.QUERY_BY_CONDITION).params(map).build()
                   .execute(new CategoryStringCallback());
    }

    private class CategoryStringCallback extends StringCallback{

        @Override
        public void onError(Call call, Exception e, int id) {
            LogUtil.i("查询类别数据失败 "+e);
            loadingDialog.dismiss();
        }

        @Override
        public void onResponse(String response, int id) {
            LogUtil.i("查询类别数据成功 response"+response);
            loadingDialog.dismiss();
            ProMsg proMsg = JSON.parseObject(response, ProMsg.class);
            List<ProMsg.ProjectsEntity> projectsEntityList = proMsg.getXmzbProjectsEntityCustom();
            Message message = Message.obtain();
            message.what = Config.PROJECT_CATEGORY_WHAT;
            message.obj = projectsEntityList;
            handler.sendMessage(message);
        }
    }
}

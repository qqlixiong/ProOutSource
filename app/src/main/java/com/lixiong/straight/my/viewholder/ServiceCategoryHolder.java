package com.lixiong.straight.my.viewholder;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseCategoryHolder;
import com.lixiong.straight.common.Category;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.bean.ServiceCategory;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;

/**
 * Created by john on 2017/6/1.
 */

public class ServiceCategoryHolder extends BaseCategoryHolder {

    @Bind(R.id.rg_service_category)
    RadioGroup rgServiceCategory;
    @Bind(R.id.ser_cate_flow_layout)
    TagFlowLayout serCateFlowLayout;

    public ServiceCategoryHolder() {
        super();
        Category.getInstance().createRadioButton(serviceCategoryOneList,rgServiceCategory,R.drawable.service_category_backgound_selector,
                R.drawable.service_category_text_color_drawable_selector,new RgCheckedChangeListener());
        createTagFlowLayout(list1,0);
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.service_category_layout);
    }

    @Override
    protected void refreshView(Object data) {

    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected void createTagFlowLayout(final List<ServiceCategory> categoryList, final int checkedId) {
        serCateFlowLayout.setAdapter(new TagAdapter<ServiceCategory>(categoryList) {
            @Override
            public View getView(FlowLayout parent, int position, ServiceCategory serviceCategory) {
                TextView textView = (TextView) UIUtils.inflate(R.layout.ser_cate_item, serCateFlowLayout, false);
                textView.setText(serviceCategory.getItemName());
                return textView;
            }
        });

        serCateFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //一级目录的itemCode
                String itemCodeOne = serviceCategoryOneList.get(checkedId).getItemCode();
                List<String> itemCodeOneList = new ArrayList<>();
                itemCodeOneList.add(itemCodeOne);
                List<String> itemNameList = new ArrayList<>();
                List<String> itemCodeList = new ArrayList<>();
                for(int i : selectPosSet){
                    itemNameList.add(categoryList.get(i).getItemName());
                    itemCodeList.add(categoryList.get(i).getItemCode());
                }
                Message message = Message.obtain();
                message.what = Config.SERVICE_CATEGORY_SELECT_NUM;
                message.arg1 = selectPosSet.size();
                Map<String,List<String>> map = new HashMap<>();
                map.put(Config.ITEM_CODE_ONE,itemCodeOneList);
                map.put(Config.ITEM_CODE_TOW,itemCodeList);
                map.put(Config.ITEM_NAME_TOW,itemNameList);
                message.obj = map;
                handler.sendMessage(message);
            }
        });
    }
}

package com.lixiong.straight.common;

import android.support.annotation.DrawableRes;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.App;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.DisplayUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.bean.ServiceCategory;

import java.util.List;

/**
 * Created by john on 2017/6/7.
 */

public class Category {

    private Category() {

    }

    private static class Inner{
        private static Category category = new Category();
    }

    public static Category getInstance(){
        return Inner.category;
    }

    public void createRadioButton(List<ServiceCategory> list,RadioGroup radioGroup,@DrawableRes int resId,
                                   int colorId,RadioGroup.OnCheckedChangeListener listener) {
        list = JSON.parseArray(Constant.SERVICE_CATEGORY_ONE,ServiceCategory.class);
        for (int i = 0; i < list.size(); i++) {
            RadioButton tempButton = new RadioButton(App.context);
            tempButton.setBackgroundResource(resId);// 设置RadioButton的背景图片
            tempButton.setButtonDrawable(android.R.color.transparent);           // 设置按钮的样式
            tempButton.setTextColor(UIUtils.getColorStateList(colorId));
            tempButton.setGravity(Gravity.CENTER);
            tempButton.setText(list.get(i).getItemName());
            tempButton.setId(i);
            radioGroup.addView(tempButton, RadioGroup.LayoutParams.MATCH_PARENT,
                    DisplayUtil.dip2px(App.context,50));
        }
        radioGroup.check(0);
        radioGroup.setOnCheckedChangeListener(listener);
    }
}

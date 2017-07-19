package com.lixiong.straight.base;

import android.os.Handler;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.bean.ServiceCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2017/6/7.
 */

public abstract class BaseCategoryHolder extends BaseHolder{
    //一级目录的信息
    protected List<ServiceCategory> serviceCategoryOneList;
    //所有的二级服务类别
    protected List<ServiceCategory> serviceCategoryTowList = new ArrayList<>();
    //存放每个一级服务类别的集合
    protected List<ServiceCategory> list1 = new ArrayList<>();
    protected List<ServiceCategory> list2 = new ArrayList<>();
    protected List<ServiceCategory> list3 = new ArrayList<>();
    protected List<ServiceCategory> list4 = new ArrayList<>();
    protected List<ServiceCategory> list5 = new ArrayList<>();
    protected List<ServiceCategory> list6 = new ArrayList<>();
    protected List<ServiceCategory> list7 = new ArrayList<>();
    protected List<ServiceCategory> list8 = new ArrayList<>();
    protected List<ServiceCategory> list9 = new ArrayList<>();
    protected List<ServiceCategory> list10 = new ArrayList<>();
    protected List<ServiceCategory> list11 = new ArrayList<>();
    protected List<ServiceCategory> list12 = new ArrayList<>();
    protected List<ServiceCategory> list13 = new ArrayList<>();
    protected List<ServiceCategory> list14 = new ArrayList<>();
    protected List<ServiceCategory> list15 = new ArrayList<>();
    protected List<ServiceCategory> list16 = new ArrayList<>();
    protected List<ServiceCategory> list17 = new ArrayList<>();
    protected List<ServiceCategory> list18 = new ArrayList<>();
    protected List<ServiceCategory> list19 = new ArrayList<>();
    protected Handler handler;

    public BaseCategoryHolder() {
        serviceCategoryTowList = JSON.parseArray(Constant.SERVICE_CATEGORY_TOW,ServiceCategory.class);
        int size = serviceCategoryTowList.size();
        LogUtil.i("二级服务类目总数："+size);
        for(int i=0;i<size;i++){
            if(i>=0&&i<16){
                list1.add(serviceCategoryTowList.get(i));
            }else if (i>=16&&i<36){
                list2.add(serviceCategoryTowList.get(i));
            }else if (i>=36&&i<44){
                list3.add(serviceCategoryTowList.get(i));
            }else if (i>=44&&i<60){
                list4.add(serviceCategoryTowList.get(i));
            }else if (i>=60&&i<75){
                list5.add(serviceCategoryTowList.get(i));
            }else if (i>=75&&i<88){
                list6.add(serviceCategoryTowList.get(i));
            }else if (i>=88&&i<100){
                list7.add(serviceCategoryTowList.get(i));
            }else if (i>=100&&i<117){
                list8.add(serviceCategoryTowList.get(i));
            }else if (i>=117&&i<137){
                list9.add(serviceCategoryTowList.get(i));
            }else if (i>=137&&i<159){
                list10.add(serviceCategoryTowList.get(i));
            }else if (i>=159&&i<176){
                list11.add(serviceCategoryTowList.get(i));
            }else if (i>=176&&i<209){
                list12.add(serviceCategoryTowList.get(i));
            }else if (i>=209&&i<226){
                list13.add(serviceCategoryTowList.get(i));
            }else if (i>=226&&i<245){
                list14.add(serviceCategoryTowList.get(i));
            }else if (i>=245&&i<260){
                list15.add(serviceCategoryTowList.get(i));
            }else if (i>=260&&i<277){
                list16.add(serviceCategoryTowList.get(i));
            }else if (i>=277&&i<287){
                list17.add(serviceCategoryTowList.get(i));
            }else if (i>=287&&i<298){
                list18.add(serviceCategoryTowList.get(i));
            }else if (i>=298&&i<311){
                list19.add(serviceCategoryTowList.get(i));
            }
        }
        serviceCategoryOneList = JSON.parseArray(Constant.SERVICE_CATEGORY_ONE,ServiceCategory.class);
    }

    public String[] getPriceArray(){
        return UIUtils.getStringArr(R.array.arrayPrice);
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public class RgCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(handler != null){
                handler.sendEmptyMessage(Config.SERVICE_CATEGORY_CHECKED_CHANGE);
            }
            switch (checkedId){
                case 0:
                    createTagFlowLayout(list1,0);
                    break;

                case 1:
                    createTagFlowLayout(list2,1);
                    break;

                case 2:
                    createTagFlowLayout(list3,2);
                    break;

                case 3:
                    createTagFlowLayout(list4,3);
                    break;

                case 4:
                    createTagFlowLayout(list5,4);
                    break;

                case 5:
                    createTagFlowLayout(list6,5);
                    break;

                case 6:
                    createTagFlowLayout(list7,6);
                    break;

                case 7:
                    createTagFlowLayout(list8,7);
                    break;

                case 8:
                    createTagFlowLayout(list9,8);
                    break;

                case 9:
                    createTagFlowLayout(list10,9);
                    break;

                case 10:
                    createTagFlowLayout(list11,10);
                    break;

                case 11:
                    createTagFlowLayout(list12,11);
                    break;

                case 12:
                    createTagFlowLayout(list13,12);
                    break;

                case 13:
                    createTagFlowLayout(list14,13);
                    break;

                case 14:
                    createTagFlowLayout(list15,14);
                    break;

                case 15:
                    createTagFlowLayout(list16,15);
                    break;

                case 16:
                    createTagFlowLayout(list17,16);
                    break;

                case 17:
                    createTagFlowLayout(list18,17);
                    break;

                case 18:
                    createTagFlowLayout(list19,18);
                    break;
            }
        }
    }

    protected abstract void createTagFlowLayout(List<ServiceCategory> categoryList, int checkedId);
}

package com.lixiong.straight.common.utils;

import android.view.View;
import android.widget.ImageView;

/**
 * Created by john on 2017/4/26.
 */

public class ViewUtils {
    /**
     * 隐藏view
     *
     * @param views
     */
    public static void hideView(View... views) {
        for(View view : views){
            if (view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 消失view
     *
     * @param views
     */
    public static void goneView(View... views) {
        for(View view : views){
            if (view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 显示view
     *
     * @param views
     */
    public static void showView(View... views) {
        for(View view : views){
            if (view.getVisibility() == View.INVISIBLE || view.getVisibility() == View.GONE) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 判断view是否为显示状态
     *
     * @param view
     * @return true为显示状态   false为隐藏状态
     */
    public static boolean isShowView(View view) {
        return view.getVisibility() == View.VISIBLE ? true : false;
    }

    /**
     * 设置ImageView 显示的图片背景
     * @param view                     ImageView
     * @param resId                   原资源tag
     * @param targetResId             目标资源tag
     * @param imageId                目标资源图片
     */
    public static void setImageView(ImageView view, int resId, int targetResId,
                              int imageId){
        if(view.getTag().equals(UIUtils.getString(resId))){
            view.setImageResource(imageId);
            view.setTag(UIUtils.getString(targetResId));
        }
    }

    /**
     * 是否改变了view的状态
     * @param view       控件
     * @param resId      原资源tag
     * @return
     */
    public static boolean isChange(View view,int resId){
        return view.getTag().equals(UIUtils.getString(resId));
    }
}

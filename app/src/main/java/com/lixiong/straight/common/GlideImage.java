package com.lixiong.straight.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideImage {
    private GlideImage() {
    }

    private static class Inner{
        private static GlideImage glideImage = new GlideImage();
    }

    public static GlideImage getInstance(){
        return Inner.glideImage;
    }

    /**
     * 加载图片到ImageView
     * @param context      上下文
     * @param path         图片路径
     * @param resourceId   加载图片错误时显示到ImageView的资源ID
     * @param imageViews   加载图片的控件
     */
    public void displayImage(Context context, Object path, int resourceId,ImageView... imageViews) {
        for(ImageView imageView : imageViews){
            Glide.with(context).load(path).error(resourceId).into(imageView);
        }
    }

}

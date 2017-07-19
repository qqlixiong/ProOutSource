package com.lixiong.straight.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;

/**
 * Created by john on 2017/6/9.
 */

public class BitmapUtil {

    public static Bitmap getImageThumbnail(String imagePath) {

        System.out.println("getImageThumbnail - imagePath: " + imagePath);

        Bitmap bitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;  //不申请内存 计算图片比例

        // 获取这个图片的宽和高，注意此处的bitmap为null
        bitmap = BitmapFactory.decodeFile(imagePath, options);

        options.inJustDecodeBounds = false; //设为 false  申请内存
        // 计算缩放比
        int h = options.outHeight;
        int w = options.outWidth;
        int beWidth  = w / Config.VIDEO_WIDTH;
        int beHeight = h / Config.VIDEO_HEIGHT;
        int be = 4;
        if (beWidth < beHeight && beHeight >= 1) {
            be = beHeight;
        }
        if (beHeight< beWidth  && beWidth >= 1) {
            be = beWidth;
        }

        if (be <= 0) {
            be = 1;
        } else if (be > 3) {
            be = 3;
        }

        options.inSampleSize = be;
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        options.inPurgeable = true;
        options.inInputShareable = true;
        try {
            // 重新读入图片，读取缩放后的bitmap，注意这次要把options.inJustDecodeBounds 设为 false
            bitmap = BitmapFactory.decodeFile(imagePath, options);

            // 利用ThumbnailUtils来创建缩略图，这里要指定要缩放哪个Bitmap对象
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, Config.VIDEO_WIDTH, Config.VIDEO_HEIGHT,
                    ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        } catch (OutOfMemoryError e) {
            LogUtil.e("内存溢出："+e);
            System.gc();
            bitmap = null;
        }

        return bitmap;
    }
}

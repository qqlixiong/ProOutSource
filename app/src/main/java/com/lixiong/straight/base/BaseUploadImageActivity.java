package com.lixiong.straight.base;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.BitmapUtil;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.PicUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.my.HeadSculptureDialog;

/**
 * Created by john on 2017/6/8.
 */

public abstract class BaseUploadImageActivity extends AppCompatActivity implements IActivity, View.OnClickListener {
    //获取选择图片的Bitmap
    protected Bitmap bitmap;
    protected HeadSculptureDialog headSculptureDialog;

    protected void showSculptureDialog(){
        headSculptureDialog = new HeadSculptureDialog(this, this);
        headSculptureDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_take_picture:
                takePhotograph();
                break;

            case R.id.tv_select_from_phone_album:
                nativeChoosePic();
                break;

            case R.id.tv_cancel:
                break;
        }
        headSculptureDialog.dismiss();
    }

    /**
     * 从本地选择已有的图片
     */
    public void nativeChoosePic(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, Config.PICK_IMAGE_FROM_ALBUM);
    }

    //获取图片的Base64字符码
    public String getPicStr(){
        return bitmap == null ? "" : PicUtil.bitmaptoString(bitmap);
    }

    /**
     * 拍照
     */
    public void takePhotograph() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, Config.TAKE_PHOTO_REQUEST_TWO);
    }

    //拍照返回的图片
    public void takePhotoResult(int resultCode, Activity activity, Intent data,
                                       ImageView imageView){
        if (resultCode == activity.RESULT_CANCELED) {
            UIUtils.toast("取消拍照");
            return;
        }
        clearBitmap();
        bitmap = data.getParcelableExtra("data");
        imageView.setImageBitmap(bitmap);
    }

    //从本地返回的图片
    public void pickImageFromAlbumResult(int resultCode, Activity activity, Intent data,
                                                ImageView imageView){
        if (resultCode == activity.RESULT_CANCELED) {
            UIUtils.toast("取消选择");
            return;
        }
        try {
            Uri imageUri = data.getData();
            LogUtil.i("选择的图片路径："+imageUri.toString());
            clearBitmap();
            String picPath = imageUri.toString();
            if(picPath.contains("file://")){
                picPath = picPath.replace("file://","");
            }
            LogUtil.i("选择的图片路径2："+picPath);
            bitmap = BitmapUtil.getImageThumbnail(picPath);
            LogUtil.e("选取手机相册的图片bitmap："+bitmap);
            imageView.setImageURI(imageUri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearBitmap() {
        if(bitmap != null){
            bitmap = null;
        }
    }

    @Override
    protected void onDestroy() {
        if(bitmap !=null && !bitmap.isRecycled()){
            bitmap.recycle();
        }
        super.onDestroy();
    }
}

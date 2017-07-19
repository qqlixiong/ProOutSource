package com.lixiong.straight.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.lixiong.straight.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Dialog基类。
 */
public abstract class BaseCustomDialog {

    private final WeakReference<Activity> mActivityRef;
    private OnClickListener mOnClickListener;
    private Dialog mDialog;
    private Window dialogWindow;
    private WindowManager.LayoutParams lp;
    private Context mContext;

    /**
     * 是否弹出键盘
     *
     * @return
     */
    protected boolean isShowKeyboardWhenStart() {
        return false;
    }

    /**
     * 传入的布局resourceID
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 需要加监听的IDlist
     *
     * @return
     */
    protected abstract List<Integer> getConcernedIds();

    /**
     * 做一些初始化界面的操作
     */
    protected abstract void initView(View view);

    /**
     * 显示布局时是否充满整个屏幕
     *
     * @return
     */
    protected boolean isFillWindow() {
        return false;
    }

    /**
     * 设置对话框的显示位置
     */
    protected abstract int gravity();

    /**
     *
     * lp.x与lp.y表示相对于原始位置的偏移.
     * 当参数值包含Gravity.LEFT时,对话框出现在左边,所以lp.x就表示相对左边的偏移,负值忽略.
     * 当参数值包含Gravity.RIGHT时,对话框出现在右边,所以lp.x就表示相对右边的偏移,负值忽略.
     * 当参数值包含Gravity.TOP时,对话框出现在上边,所以lp.y就表示相对上边的偏移,负值忽略.
     * 当参数值包含Gravity.BOTTOM时,对话框出现在下边,所以lp.y就表示相对下边的偏移,负值忽略.
     * 当参数值包含Gravity.CENTER_HORIZONTAL时
     * ,对话框水平居中,所以lp.x就表示在水平居中的位置移动lp.x像素,正值向右移动,负值向左移动.
     * 当参数值包含Gravity.CENTER_VERTICAL时
     * ,对话框垂直居中,所以lp.y就表示在垂直居中的位置移动lp.y像素,正值向右移动,负值向左移动.
     * gravity的默认值为Gravity.CENTER,即Gravity.CENTER_HORIZONTAL |
     * Gravity.CENTER_VERTICAL.
     *
     * 本来setGravity的参数值为Gravity.LEFT | Gravity.TOP时对话框应出现在程序的左上角,但在
     * 我手机上测试时发现距左边与上边都有一小段距离,而且垂直坐标把程序标题栏也计算在内了,
     * Gravity.LEFT, Gravity.TOP, Gravity.BOTTOM与Gravity.RIGHT都是如此,据边界有一小段距离
     *
     * lp.x = 100; // 新位置X坐标
     * lp.y = 100; // 新位置Y坐标
     * lp.width = 300; // 宽度
     * lp.height = 300; // 高度
     * lp.alpha = 0.7f; // 透明度
     * 当Window的Attributes改变时系统会调用此函数,可以直接调用以应用上面对窗口参数的更改,也可以用setAttributes
     * dialog.onWindowAttributesChanged(lp);
     */
    protected abstract WindowManager.LayoutParams lp();

    protected WindowManager.LayoutParams getLp() {
        return dialogWindow.getAttributes();
    }

    /**
     * 是否点击弹出框其他区域销毁对话框。
     *
     * @return
     */
    protected boolean isCanceledOnTouchOutside() {
        return true;
    }

    protected boolean isCanceledOnBackKey() {
        return true;
    }

    public BaseCustomDialog(Activity activity, OnClickListener onClickListener) {
        mContext = activity.getApplicationContext();
        mActivityRef = new WeakReference<Activity>(activity);
        mOnClickListener = onClickListener;
    }

    /**
     * dialog显示的方法
     */
    public void show() {
        Activity activity = mActivityRef.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }

        mDialog = new Dialog(activity, R.style.DialogTheme_CenterCustom);

        View rootView = LayoutInflater.from(activity).inflate(getLayoutId(), null);
        initView(rootView);
        dialogWindow = mDialog.getWindow();
        int gravity = gravity();
        if(gravity != 0){
            dialogWindow.setGravity(gravity());
        }
        WindowManager.LayoutParams layoutParams = lp();
        if(layoutParams != null){
            dialogWindow.setAttributes(lp());
        }
        if (isFillWindow()) {
            dialogWindow.setContentView(rootView);
        } else {
            mDialog.setContentView(rootView);
        }
        mDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside());
        mDialog.setCancelable(isCanceledOnBackKey());
        addListeners(rootView);

        mDialog.show();
    }

    /**
     * 对布局中的子控件添加监听，若 getConcernedIds() 为空则遍历所有子控件并添加监听
     *
     * @param view 根布局
     */
    private void addListeners(View view) {
        List<Integer> concernedIds = getConcernedIds();
        if (concernedIds == null || concernedIds.isEmpty()) {
            List<View> viewList = pickViewGroup(view);
            for (View item : viewList) {
                item.setOnClickListener(mOnClickListener);
            }
        } else {
            int concernedCount = concernedIds.size();
            for (int i = 0; i < concernedCount; i++) {
                view.findViewById(concernedIds.get(i)).setOnClickListener(mOnClickListener);
            }
        }
    }

    /**
     * 遍历View子控件若为ViewGroup则放入ViewGroupList，若为View则放入ViewList
     */
    private List<View> pickViewGroup(View view) {
        List<View> viewList = new ArrayList<View>();
        List<View> groupList = new ArrayList<View>();
        groupList.add(view);
        int viewGroupListCount = groupList.size();
        View item = null;
        int tempCount = 0;
        View tempView;
        while (viewGroupListCount > 0) {
            for (int i = 0; i < viewGroupListCount; i++) {
                item = groupList.get(0);// 因为有remove操作，所以每次拿列表的首个，或者最后一个都可
                if (item instanceof ViewGroup) {
                    groupList.remove(item);

                    tempCount = ((ViewGroup) item).getChildCount();
                    for (int j = 0; j < tempCount; j++) {
                        tempView = ((ViewGroup) item).getChildAt(j);
                        if (tempView instanceof ViewGroup) {
                            groupList.add(tempView);
                        } else {
                            viewList.add(tempView);
                        }
                    }
                } else {
                    viewList.add(item);
                }
            }
            viewGroupListCount = groupList.size();
        }
        return viewList;
    }

    /**
     * 关闭dialog
     */
    public void dismiss() {

        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}

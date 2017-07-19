package com.lixiong.straight.my;

import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseCustomDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by john on 2017/5/31.
 */

public class HeadSculptureDialog extends BaseCustomDialog {
    public HeadSculptureDialog(Activity activity, View.OnClickListener onClickListener) {
        super(activity, onClickListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.head_sculpture_dialog;
    }

    @Override
    protected List<Integer> getConcernedIds() {
        List<Integer> concernedIds = new ArrayList<>();
        concernedIds.add(R.id.tv_take_picture);
        concernedIds.add(R.id.tv_select_from_phone_album);
        concernedIds.add(R.id.tv_cancel);
        return concernedIds;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected int gravity() {
        return Gravity.BOTTOM|Gravity.LEFT|Gravity.RIGHT;
    }

    @Override
    protected WindowManager.LayoutParams lp() {
        return null;
    }
}

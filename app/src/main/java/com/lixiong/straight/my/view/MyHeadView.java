package com.lixiong.straight.my.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by john on 2017/6/4.
 */

public class MyHeadView extends FrameLayout {

    @Bind(R.id.tv_my_head_name)
    TextView tvMyHeadName;
    @Bind(R.id.iv_my_head)
    ImageView ivMyHead;

    public MyHeadView(Context context) {
        this(context, null);
    }

    public MyHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = UIUtils.inflate(R.layout.my_head_view_item, this);
        ButterKnife.bind(this, view);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyHeadView);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int id = array.getIndex(i);
            switch (id){
                case R.styleable.MyHeadView_iv_my_function_picture:
                    ivMyHead.setImageDrawable(array.getDrawable(id));
                    break;

                case R.styleable.MyHeadView_tv_my_function_name:
                    tvMyHeadName.setText(array.getString(id));
                    break;
            }
        }
        array.recycle();
    }
}

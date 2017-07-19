package com.lixiong.straight.my.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by john on 2017/5/26.
 */

public class MyView extends FrameLayout {
    @Nullable
    @Bind(R.id.iv_my)
    ImageView ivMy;
    @Nullable
    @Bind(R.id.tv_my_name)
    TextView tvMyName;
    @Nullable
    @Bind(R.id.my_line)
    View myLine;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = UIUtils.inflate(R.layout.my_item_layout, this);
        ButterKnife.bind(this, view);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int id = array.getIndex(i);
            switch (id) {
                case R.styleable.MyView_iv_my:
                    ivMy.setImageDrawable(array.getDrawable(id));
                    break;

                case R.styleable.MyView_tv_my_name:
                    tvMyName.setText(array.getString(id));
                    break;

                case R.styleable.MyView_has_my_line:
                    if (!array.getBoolean(id, true)) {
                        ViewUtils.goneView(myLine);
                    }
                    break;
            }
        }
        array.recycle();
    }

    public void setTvMyName(String myName){
        tvMyName.setText(myName);
    }
}

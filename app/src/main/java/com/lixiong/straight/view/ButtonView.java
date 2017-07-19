package com.lixiong.straight.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.UIUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by john on 2017/5/28.
 */

public class ButtonView extends FrameLayout {
    @Bind(R.id.tv_btn)
    TextView tvBtn;

    public ButtonView(Context context) {
        this(context, null);
    }

    public ButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = UIUtils.inflate(R.layout.login_register_btn_item, this);
        ButterKnife.bind(this, view);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.ButtonView);
        for(int i=0;i<array.getIndexCount();i++){
            int id = array.getIndex(i);
            if(id == R.styleable.ButtonView_btn_text){
                tvBtn.setText(array.getString(id));
            }
        }
        array.recycle();
    }

    public void setTextBtn(String textBtn){
        tvBtn.setText(textBtn);
    }
}

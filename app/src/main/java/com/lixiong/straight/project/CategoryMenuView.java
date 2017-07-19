package com.lixiong.straight.project;

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

public class CategoryMenuView extends FrameLayout {
    @Bind(R.id.tv_category)
    TextView tvCategory;

    public CategoryMenuView(Context context) {
        this(context, null);
    }

    public CategoryMenuView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = UIUtils.inflate(R.layout.category_menu_item, this);
        ButterKnife.bind(this, view);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CategoryMenuView);
        for(int i=0;i<array.getIndexCount();i++){
            int id = array.getIndex(i);
            if(id == R.styleable.CategoryMenuView_tv_category){
                tvCategory.setText(array.getString(id));
            }
        }
        array.recycle();
    }
}

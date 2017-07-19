package com.lixiong.straight.my.view;

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
 * Created by john on 2017/6/7.
 */

public class NoDataView extends FrameLayout {
    @Bind(R.id.tv_no_data_a)
    TextView tvNoDataA;
    @Bind(R.id.tv_no_data_b)
    TextView tvNoDataB;

    public NoDataView(Context context) {
        this(context, null);
    }

    public NoDataView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoDataView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = UIUtils.inflate(R.layout.no_data_item, this);
        ButterKnife.bind(this, view);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NoDataView);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int id = array.getIndex(i);
            switch (id) {
                case R.styleable.NoDataView_tv_no_data_a:
                    tvNoDataA.setText(array.getString(id));
                    break;

                case R.styleable.NoDataView_tv_no_data_b:
                    tvNoDataB.setText(array.getString(id));
                    break;
            }
        }
        array.recycle();
    }
}

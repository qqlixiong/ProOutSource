package com.lixiong.straight.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by john on 2017/5/26.
 */

public class TopBar extends FrameLayout {
    @Nullable
    @Bind(R.id.tv_title_name)
    TextView tvTitleName;
    @Nullable
    @Bind(R.id.tv_title_skill)
    TextView tvTitleSkill;
    @Nullable
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Nullable
    @Bind(R.id.iv_top_a)
    ImageView ivTopA;
    @Nullable
    @Bind(R.id.iv_top_b)
    ImageView ivTopB;
    @Bind(R.id.rl_top_back)
    RelativeLayout rlTopBack;
    @Bind(R.id.rl_chitchat)
    RelativeLayout rlChitchat;
    @Nullable
    @Bind(R.id.rl_save)
    RelativeLayout rlSave;
    @Bind(R.id.tv_save)
    TextView tvSave;
    @Nullable
    @Bind(R.id.iv_save)
    ImageView ivSave;
    private Context context;

    public RelativeLayout getRlSave() {
        return rlSave;
    }

    //分享
    @Nullable
    public ImageView getIvTopA() {
        return ivTopA;
    }

    //收藏
    @Nullable
    public ImageView getIvTopB() {
        return ivTopB;
    }

    @Nullable
    public TextView getTvTitle() {
        return tvTitle;
    }

    @Nullable
    public ImageView getIvSave() {
        return ivSave;
    }

    public TextView getTvSave() {
        return tvSave;
    }

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        View view = UIUtils.inflate(R.layout.top_bar_layout, this);
        ButterKnife.bind(this, view);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int id = array.getIndex(i);
            switch (id) {
                case R.styleable.TopBar_top_bar_type:
                    switch (array.getInt(id, 0)) {
                        case 0:
                            break;

                        case 1:
                            ViewUtils.goneView(tvTitle);
                            ViewUtils.showView(rlChitchat);
                            break;

                        case 2:
                            ViewUtils.showView(ivTopA, ivTopB);
                            break;

                        case 3:
                            ViewUtils.showView(tvSave);
                            break;
                    }
                    break;

                case R.styleable.TopBar_tv_title:
                    tvTitle.setText(array.getString(id));
                    break;

                case R.styleable.TopBar_tv_title_name:
                    tvTitleName.setText(array.getString(id));
                    break;

                case R.styleable.TopBar_tv_title_skill:
                    tvTitleSkill.setText(array.getString(id));
                    break;

                case R.styleable.TopBar_has_top_back:
                    if (!array.getBoolean(id, true)) {
                        ViewUtils.goneView(rlTopBack);
                    }
                    break;

                case R.styleable.TopBar_has_save_btn:
                    if (array.getBoolean(id, false)) {
                        ViewUtils.showView(rlSave);
                    }
                    break;
            }
        }
        array.recycle();
    }

    public RelativeLayout getRlTopBack() {
        return rlTopBack;
    }

    @OnClick(R.id.rl_top_back)
    public void onViewClicked() {
        if (context instanceof Activity) {
            ((Activity) context).onBackPressed();
        }
    }
}

package com.lixiong.straight.my.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by john on 2017/5/24.
 */

public class CustomMyView extends FrameLayout {
    @Nullable
    @Bind(R.id.tv_my_title)
    TextView tvMyTitle;
    @Nullable
    @Bind(R.id.iv_my_go)
    ImageView ivMyGo;
    @Nullable
    @Bind(R.id.iv_my_pic)
    CircleImageView ivMyPic;
    @Nullable
    @Bind(R.id.tv_my_resume)
    TextView tvMyResume;
    @Nullable
    @Bind(R.id.et_my_resume)
    EditText etMyResume;
    @Nullable
    @Bind(R.id.my_bottom_view)
    View myBottomView;
    @Nullable
    @Bind(R.id.custom_switch_view)
    SwitchView customSwitchView;
    @Nullable
    @Bind(R.id.iv_my)
    ImageView ivMy;

    @Nullable
    public ImageView getIvMy() {
        return ivMy;
    }

    public EditText getEtMyResume() {
        return etMyResume;
    }

    @Nullable
    public TextView getTvMyResume() {
        return tvMyResume;
    }

    @Nullable
    public CircleImageView getIvMyPic() {
        return ivMyPic;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomMyView(Context context) {
        this(context, null);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomMyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomMyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = UIUtils.inflate(R.layout.my_item, this);
        ButterKnife.bind(this, view);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomMyView);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int id = array.getIndex(i);
            switch (id) {
                case R.styleable.CustomMyView_my_title:
                    tvMyTitle.setText(array.getString(id));
                    break;

                case R.styleable.CustomMyView_my_resume_type:
                    switch (array.getInt(id, 0)) {
                        case 0:
                            ViewUtils.showView(ivMyPic);
                            ViewUtils.goneView(tvMyResume);
                            break;

                        case 1:
                            break;

                        case 2:
                            ViewUtils.showView(etMyResume);
                            ViewUtils.goneView(tvMyResume);
                            break;

                        case 3:
                            ViewUtils.goneView(tvMyResume);
                            break;

                        case 4:
                            ViewUtils.goneView(tvMyResume);
                            ViewUtils.showView(customSwitchView);
                            break;

                        case 5:
                            ViewUtils.goneView(tvMyResume);
                            ViewUtils.showView(ivMy);
                            break;
                    }
                    break;

                case R.styleable.CustomMyView_my_et_hint_text:
                    etMyResume.setHint(array.getString(id));
                    break;

                case R.styleable.CustomMyView_my_src:
                    ivMyPic.setImageDrawable(array.getDrawable(id));
                    break;

                case R.styleable.CustomMyView_my_tv_text:
                    tvMyResume.setText(array.getString(id));
                    break;

                case R.styleable.CustomMyView_has_bottom_line:
                    if (!array.getBoolean(id, true)) {
                        ViewUtils.goneView(myBottomView);
                    }
                    break;

                case R.styleable.CustomMyView_has_iv_go:
                    if (!array.getBoolean(id, true)) {
                        ViewUtils.goneView(ivMyGo);
                    }
                    break;
            }
        }
        array.recycle();
    }

    /**
     * 设置底线为消失
     */
    public void goneMyBottomView(){
        ViewUtils.goneView(myBottomView);
    }

    /**
     * 设置底线为显示
     */
    public void showMyBottomView(){
        ViewUtils.showView(myBottomView);
    }
}

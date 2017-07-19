package com.lixiong.straight.my.settings.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by john on 2017/5/26.
 */
public class ModifyView extends FrameLayout {
    @Nullable
    @Bind(R.id.tv_phone_name)
    TextView tvPhoneName;
    @Nullable
    @Bind(R.id.iv_modify)
    ImageView ivModify;
    @Nullable
    @Bind(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @Nullable
    @Bind(R.id.et_text)
    EditText etText;
    @Nullable
    @Bind(R.id.rl_code)
    RelativeLayout rlCode;
    @Nullable
    @Bind(R.id.btn_get_code)
    Button btnGetCode;
    @Nullable
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    public Button getBtnGetCode() {
        return btnGetCode;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    @Nullable
    public EditText getEtText() {
        return etText;
    }

    /**
     * 设置手机号码
     * @param phoneNum  手机号码
     */
    public void setTextPhone(String phoneNum){
        tvPhoneNum.setText(phoneNum);
    }

    public ModifyView(Context context) {
        this(context, null);
    }

    public ModifyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ModifyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = UIUtils.inflate(R.layout.modify_item, this);
        ButterKnife.bind(this, view);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ModifyView);
        for (int i = 0; i < array.getIndexCount(); i++) {
            int id = array.getIndex(i);
            switch (id) {
                case R.styleable.ModifyView_modify_type:
                    switch (array.getInt(id, 0)) {
                        case 0:
                            ViewUtils.showView(tvPhoneName, tvPhoneNum);
                            ViewUtils.hideView(ivModify, etText);//ivModify不能设置为消失
                            break;

                        case 1:
                            ViewUtils.showView(rlCode);
                            break;

                        case 2:
                            ViewUtils.showView(tvPhoneName);
                            ViewUtils.hideView(ivModify);//ivModify不能设置为消失
                            break;

                        case 3:        //修改密码
                            break;
                    }
                    break;

                case R.styleable.ModifyView_tv_phone_name:
                    tvPhoneName.setText(array.getString(id));
                    break;

                case R.styleable.ModifyView_et_hint_text:
                    etText.setHint(array.getString(id));
                    break;

                case R.styleable.ModifyView_iv_modify:
                    ivModify.setImageDrawable(array.getDrawable(id));
                    break;

            }
        }
        array.recycle();
    }

    public void setImageDrawable(@Nullable Drawable drawable){
        ivModify.setImageDrawable(drawable);
    }
}

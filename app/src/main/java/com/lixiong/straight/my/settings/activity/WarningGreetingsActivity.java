package com.lixiong.straight.my.settings.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.my.view.CustomMyView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import butterknife.Bind;
import me.codeboy.android.aligntextview.AlignTextView;

public class WarningGreetingsActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.cmv_call_language)
    CustomMyView cmvCallLanguage;
    @Bind(R.id.tfl_waining_greeting)
    TagFlowLayout tflWainingGreeting;
    private SharedPreferenceUtil sharedPreferenceUtil;
    //打招呼语
    private String[] callLanguage;

    @Override
    public int getLayoutId() {
        return R.layout.activity_warning_greetings;
    }

    @Override
    public void initData() {
        sharedPreferenceUtil = new SharedPreferenceUtil(this, Config.SP);
        callLanguage = Constant.PERSON_CALL_LANGUAGE;
        //已经存储的打招呼语
        final int currentCallLanguageIndex = sharedPreferenceUtil.get(Config.CALL_LANGUAGE_INDEX,-1);
        tflWainingGreeting.setAdapter(new TagAdapter<String>(callLanguage) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = UIUtils.inflate(R.layout.call_language_item,tflWainingGreeting,false);
                AlignTextView alignTextView = (AlignTextView) view.findViewById(R.id.atv_call_language);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_call_language);
                if(currentCallLanguageIndex != -1 && currentCallLanguageIndex == position){
                    alignTextView.setTextColor(UIUtils.getColor(R.color.color_status_bar));
                    imageView.setImageDrawable(UIUtils.getDrawable(R.drawable.noot_clicked));
                }else {
                    alignTextView.setTextColor(UIUtils.getColor(R.color.color_call_language_text));
                    imageView.setImageDrawable(UIUtils.getDrawable(R.drawable.click));
                }
                alignTextView.setText(s);
                return view;
            }
        });

        tflWainingGreeting.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                for(int i=0;i<parent.getChildCount();i++){
                    View child = parent.getChildAt(i);
                    AlignTextView alignTextView = (AlignTextView) child.findViewById(R.id.atv_call_language);
                    ImageView imageView = (ImageView) child.findViewById(R.id.iv_call_language);
                    if(i==position){
                        alignTextView.setTextColor(UIUtils.getColor(R.color.color_status_bar));
                        imageView.setImageDrawable(UIUtils.getDrawable(R.drawable.noot_clicked));
                        sharedPreferenceUtil.set(Config.CALL_LANGUAGE,callLanguage[position]);
                        sharedPreferenceUtil.set(Config.CALL_LANGUAGE_INDEX,position);
                    }else {
                        alignTextView.setTextColor(UIUtils.getColor(R.color.color_call_language_text));
                        imageView.setImageDrawable(UIUtils.getDrawable(R.drawable.click));
                    }
                }
                return true;
            }
        });
    }
}

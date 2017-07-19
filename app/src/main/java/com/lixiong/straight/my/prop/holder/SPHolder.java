package com.lixiong.straight.my.prop.holder;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseHolder;
import com.lixiong.straight.common.utils.LogUtil;
import com.lixiong.straight.common.utils.PublicTools;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.my.prop.bean.CardDetail;
import com.lixiong.straight.my.prop.bean.CardPrice;
import com.lixiong.straight.view.TopBar;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.codeboy.android.aligntextview.AlignTextView;

/**
 * Created by john on 2017/6/10.
 */

public class SPHolder extends BaseHolder<CardDetail.XmzbToolsEntityCustomBean> implements View.OnClickListener {
    @Bind(R.id.top_shopping_prop)
    TopBar topShoppingProp;
    @Bind(R.id.tfl_prop_price)
    TagFlowLayout tflPropPrice;
    @Bind(R.id.atv_prop_z)
    AlignTextView atvPropZ;
    private Activity activity;

    public SPHolder(Activity activity) {
        this.activity = activity;
    }

    @Override
    protected View initView() {
        return UIUtils.inflate(R.layout.shopping_prop_detail_layout);
    }

    @Override
    protected void refreshView(CardDetail.XmzbToolsEntityCustomBean data) {
        loadData(data);
    }

    private void loadData(CardDetail.XmzbToolsEntityCustomBean card) {
        topShoppingProp.getTvTitle().setText(card.getToolName());
        String cardPrice = card.getPrice();
        String cardPriceType = card.getPriceType();
        final List<CardPrice> cardList = new ArrayList<>();
        String[] priceS = cardPrice.split(",");
        String[] priceTypeS = cardPriceType.split(",");
        for (int i = 0; i < priceS.length; i++) {
            CardPrice cardPri = new CardPrice(priceS[i], priceTypeS[i]);
            cardList.add(cardPri);
        }
        LogUtil.i("道具价格：" + cardList.toString());
        tflPropPrice.setAdapter(new TagAdapter<CardPrice>(cardList) {
            @Override
            public View getView(FlowLayout parent, int position, CardPrice cardPrice) {
                View view = UIUtils.inflate(R.layout.card_price_item);
                ImageView ivCardPrice = (ImageView) view.findViewById(R.id.iv_card_price_type);
                TextView tvCardPriceType = (TextView) view.findViewById(R.id.tv_card_price_type);
                TextView tvCardPrice = (TextView) view.findViewById(R.id.tv_card_price);
                View lineView = view.findViewById(R.id.shopping_prop_line_x);
                ivCardPrice.setImageDrawable(UIUtils.getDrawable(R.drawable.button_icon));
                tvCardPriceType.setText(cardPrice.getPriceType());
                tvCardPrice.setText(cardPrice.getPrice());
                if (position == cardList.size() - 1) {
                    ViewUtils.goneView(lineView);
                }
                return view;
            }
        });
        tflPropPrice.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    View child = parent.getChildAt(i);
                    ImageView ivCardPrice = (ImageView) child.findViewById(R.id.iv_card_price_type);
                    if (i == position) {
                        ivCardPrice.setImageDrawable(UIUtils.getDrawable(R.drawable.button));
                    } else {
                        ivCardPrice.setImageDrawable(UIUtils.getDrawable(R.drawable.button_icon));
                    }
                }
                return true;
            }
        });
        atvPropZ.setText(card.getDescription());
        topShoppingProp.getRlTopBack().setOnClickListener(this);
    }

    @OnClick({R.id.tv_shopping_prop_buy,R.id.rl_top_back})
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_top_back:
                activity.onBackPressed();
                break;

            case R.id.tv_shopping_prop_buy:
                if(PublicTools.isToLogin()){
                    PublicTools.toLogin(activity);
                }
                break;
        }
    }
}

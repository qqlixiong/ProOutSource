package com.lixiong.straight.my.prop.viewholder;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.adapter.ModelRecyclerAdapter;
import com.lixiong.straight.adapter.RecyclerItemViewId;
import com.lixiong.straight.common.GlideImage;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.my.prop.activity.ShoppingPropDetailActivity;
import com.lixiong.straight.my.prop.bean.ShoppingProp;

import butterknife.Bind;
import butterknife.OnClick;
import me.codeboy.android.aligntextview.AlignTextView;

/**
 * Created by john on 2017/5/21.
 */
@RecyclerItemViewId(R.layout.shopping_prop_item)
public class ShoppingPropHolder extends ModelRecyclerAdapter.ModelViewHolder {
    @Bind(R.id.iv_prop)
    ImageView ivProp;
    @Bind(R.id.tv_prop_name)
    TextView tvPropName;
    @Bind(R.id.tv_prop_price)
    TextView tvPropPrice;
    @Bind(R.id.atv_description)
    AlignTextView atvDescription;
    private Context context;
    private int position;
    private ShoppingProp.XmzbToolsEntityCustomBean shoppingProp;

    public ShoppingPropHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void convert(Object item, ModelRecyclerAdapter adapter, Context context, int position) {
        shoppingProp = (ShoppingProp.XmzbToolsEntityCustomBean) item;
        this.context = context;
        this.position = position;
        GlideImage.getInstance().displayImage(
                context,shoppingProp.getToolImg(),R.drawable.my_a,ivProp);
        tvPropName.setText(shoppingProp.getToolName());
        tvPropPrice.setText(shoppingProp.getPrice()+"/"+shoppingProp.getUnitInfo());
        atvDescription.setText(shoppingProp.getDescription());
    }

    @OnClick(R.id.rl_shopping_prop)
    public void onViewClicked() {
        String toolCode = shoppingProp.getToolCode();
        Bundle bundle = new Bundle();
        bundle.putString(Config.SHOPPING_PROP_TOOL_CODE,toolCode);
        IntentUtil.startActivity(context,ShoppingPropDetailActivity.class,bundle);
    }
}

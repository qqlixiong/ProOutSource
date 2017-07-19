package com.lixiong.straight.message.holder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.adapter.ModelRecyclerAdapter;
import com.lixiong.straight.adapter.RecyclerItemViewId;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.project.activity.ImmComActivity;

import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by john on 2017/5/21.
 */
@RecyclerItemViewId(R.layout.message_item)
public class MessageHolder extends ModelRecyclerAdapter.ModelViewHolder {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.iv_msg)
    CircleImageView mIvMsg;
    private Context mContext;

    public MessageHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void convert(Object item, ModelRecyclerAdapter adapter, Context context, int position) {
        this.mContext = context;
        tvName.setText((String) item);
        glideImage.displayImage(context, Constant.ImageSrc(), R.drawable.my_a, mIvMsg);
    }

    @OnClick(R.id.rl_message)
    public void onViewClicked() {
        IntentUtil.startActivity2(mContext, ImmComActivity.class);
    }
}

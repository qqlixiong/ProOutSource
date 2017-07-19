package com.lixiong.straight.my.activity;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.SharedPreferenceUtil;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.my.viewholder.ServiceCategoryHolder;

import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 服务类目
 */
public class ServiceCategoryActivity extends AppCompatActivity implements IActivity {

    @Bind(R.id.fl_service_category)
    FrameLayout flServiceCategory;
    @Bind(R.id.tv_ser_cate_selected_num)
    TextView tvSerCateSelectedNum;
    @Bind(R.id.tv_ser_cate_can_select_num)
    TextView tvSerCateCanSelectNum;
    @Nullable
    @Bind(R.id.tv_select_num_a)
    TextView tvSelectNumA;
    @Nullable
    @Bind(R.id.tv_select_num_b)
    TextView tvSelectNumB;
    @Nullable
    @Bind(R.id.tv_select_num_c)
    TextView tvSelectNumC;
    private ServiceCategoryHolder serviceCategoryHolder;
    private List<String> itemCodeOneList;
    private List<String> itemCodeTowList;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case Config.SERVICE_CATEGORY_SELECT_NUM:
                    int selectNum = msg.arg1; //选择的个数
                    int surplusNum = Config.SERVICE_CATEGORY_CAN_SELECT_TOTAL - selectNum;
                    tvSerCateSelectedNum.setText(String.valueOf(selectNum));
                    tvSerCateCanSelectNum.setText(String.valueOf(surplusNum));
                    Map<String, List<String>> map = (Map<String, List<String>>) msg.obj;
                    itemCodeOneList = map.get(Config.ITEM_CODE_ONE);
                    List<String> itemNameTowList = map.get(Config.ITEM_NAME_TOW);
                    itemCodeTowList = map.get(Config.ITEM_CODE_TOW);
                    switch (selectNum) {
                        case 0:
                            ViewUtils.goneView(tvSelectNumA, tvSelectNumB, tvSelectNumC);
                            break;

                        case 1:
                            ViewUtils.showView(tvSelectNumA);
                            ViewUtils.goneView(tvSelectNumB, tvSelectNumC);
                            tvSelectNumA.setText(itemNameTowList.get(0));
                            break;

                        case 2:
                            ViewUtils.showView(tvSelectNumA, tvSelectNumB);
                            ViewUtils.goneView(tvSelectNumC);
                            tvSelectNumA.setText(itemNameTowList.get(0));
                            tvSelectNumB.setText(itemNameTowList.get(1));
                            break;

                        case 3:
                            ViewUtils.showView(tvSelectNumA, tvSelectNumB, tvSelectNumC);
                            tvSelectNumA.setText(itemNameTowList.get(0));
                            tvSelectNumB.setText(itemNameTowList.get(1));
                            tvSelectNumC.setText(itemNameTowList.get(2));
                            break;
                    }
                    break;

                case Config.SERVICE_CATEGORY_CHECKED_CHANGE:
                    tvSerCateSelectedNum.setText("0");
                    tvSerCateCanSelectNum.setText("3");
                    ViewUtils.goneView(tvSelectNumA, tvSelectNumB, tvSelectNumC);
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_service_category;
    }

    @Override
    public void initData() {
        serviceCategoryHolder = new ServiceCategoryHolder();
        flServiceCategory.addView(serviceCategoryHolder.getView());
        serviceCategoryHolder.setHandler(handler);
    }

    @OnClick(R.id.rtv_confirm)
    public void onViewClicked() {
        SharedPreferenceUtil sharedPreferenceUtil = new SharedPreferenceUtil(this,Config.SP);
        sharedPreferenceUtil.set(Config.SERVICE_CATEGORY_ONE_STRINGS, itemCodeOneList.toArray());
        sharedPreferenceUtil.set(Config.SERVICE_CATEGORY_TOW_STRINGS,itemCodeTowList.toArray());
        finish();
    }
}

package com.lixiong.straight.my.prop.activity;

import com.lixiong.straight.R;
import com.lixiong.straight.base.BaseScrollActivity;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.my.prop.fragment.MyPropFragment;
import com.lixiong.straight.my.prop.fragment.ShoppingPropFragment;

/**
 * Created by john on 2017/6/9.
 */

public class PropActivity extends BaseScrollActivity {

    @Override
    protected void addTitles() {
        titles.add(UIUtils.getString(R.string.my_prop));
        titles.add(UIUtils.getString(R.string.shopping_prop));
    }

    @Override
    protected void addFragment() {
        fragments.add(new MyPropFragment());
        fragments.add(new ShoppingPropFragment());
    }

    @Override
    protected String getTopTitle() {
        return UIUtils.getString(R.string.prop);
    }

    @Override
    protected boolean isSelect() {
        return false;
    }
}

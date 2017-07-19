package com.lixiong.straight.login.dialog;

import android.content.Context;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.UIUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * Created by john on 2017/5/23.
 */

public class LoadingDialog {
    private KProgressHUD hud;

    public LoadingDialog(Context context) {
        hud = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(UIUtils.getString(R.string.please_wait))
                .setWindowColor(UIUtils.getColor(R.color.color_status_bar))
                .setDimAmount(0.6f)
                .setCancellable(true)
                .show();
    }

    public void dismiss() {
        hud.dismiss();
    }
}

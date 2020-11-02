package com.power.home.common.util;

import android.app.Activity;

import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.MaterialDialog;
import com.power.home.R;
import com.power.home.ui.widget.MaterialBgDialog;

import javax.inject.Inject;

/**
 * Created by ZHP on 2017/7/3.
 */

public class AlertDialogUtil {


    @Inject
    public AlertDialogUtil() {
    }

    public MaterialDialog createDialog(final Activity activity) {
        MaterialDialog dialog = new MaterialBgDialog(activity);
        dialog.titleTextColor(activity.getResources().getColor(R.color.colorBlue474E70))
                .titleTextSize(activity.getResources().getDimension(R.dimen.DIMEN_7DP))
                .contentTextColor(activity.getResources().getColor(R.color.colorBlue474E70))
                .btnTextColor(activity.getResources().getColor(R.color.colorBlack666666),
                        activity.getResources().getColor(R.color.colorAccent))
                .cornerRadius(5)
                .widthScale(0.85f);
        return dialog;
    }

    public void initDialog(final Activity activity, String title) {
        final MaterialDialog dialog = createDialog(activity);
        dialog.btnText("取消", "确定")
                .content(title)
                .title("系统提示")
                .show();
        dialog.setOnBtnClickL(null, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.cancel();
                activity.finish();
            }
        });
    }

    public void initContentBackDialog(final Activity activity, String title, String content, final onBackMethod method) {
        final MaterialDialog dialog = createDialog(activity);
        dialog.content(content);
        dialog.btnText(activity.getString(R.string.cancel), activity.getString(R.string.sure))//
                .title(title)
                .show();
        dialog.setOnBtnClickL(new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.cancel();
                method.onCancel();

            }
        }, new OnBtnClickL() {
            @Override
            public void onBtnClick() {
                dialog.cancel();
                method.onSure();
//                activity.finish();
            }
        });

    }


    public interface onBackMethod {
        void onCancel();

        void onSure();
    }
}

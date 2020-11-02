package com.power.home.common.util;

import android.graphics.Typeface;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by ZHP on 2017/7/30.
 */

public class FontUtil {

    public static void setFont(TextView textView) {
        textView.setTypeface(Typeface.createFromAsset(UIUtil.getContext().getAssets(), "din_alternate_bold.ttf"));
    }

    public static void setFont(EditText textView) {
        textView.setTypeface(Typeface.createFromAsset(UIUtil.getContext().getAssets(), "din_alternate_bold.ttf"));
    }

}

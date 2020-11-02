package com.power.home.common.util;

import android.content.ClipboardManager;
import android.content.Context;


public class ClipboardManagerUtil {

    public static void copy(String content, Context context) {
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }


    public static String paste(Context context) {
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        return cmb.getText().toString().trim();
    }
}

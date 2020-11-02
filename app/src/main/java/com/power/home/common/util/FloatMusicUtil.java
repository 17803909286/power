package com.power.home.common.util;

import com.power.home.common.Constant;
import com.power.home.data.bean.Music;

/**
 * Created by ZHP on 2020/5/16 0016.
 */
public class FloatMusicUtil {

    public static void saveStatus(Music music) {

        ACache.get(UIUtil.getContext()).put(Constant.floatStatus, music);
    }
}

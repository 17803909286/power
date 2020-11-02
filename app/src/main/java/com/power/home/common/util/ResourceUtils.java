/*
 * Copyright (C) 2015 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.power.home.common.util;

import android.content.Context;

/**
 * Desction:
 * Author:pengjianbo
 * Date:15/10/22 下午9:01
 */
public class ResourceUtils {

    public static int getLayoutId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "layout",
                context.getPackageName());
    }


    public static int getStringId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "string",
                context.getPackageName());
    }


    public static int getDrawableId(Context context, String resName) {
        return context.getResources().getIdentifier(resName,
                "drawable", context.getPackageName());
    }



    public static int getStyleId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "style",
                context.getPackageName());
    }


    public static Object getStyleableId(Context context, String resName){
        return context.getResources().getIdentifier(resName, "styleable",
                context.getPackageName());
    }



    public static int getAnimId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "anim",
                context.getPackageName());
    }


    public static int getId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "id",
                context.getPackageName());
    }


    public static int getColorId(Context context, String resName) {
        return context.getResources().getIdentifier(resName, "color",
                context.getPackageName());
    }
}

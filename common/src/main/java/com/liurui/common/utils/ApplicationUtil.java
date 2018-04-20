package com.liurui.common.utils;

import android.content.Context;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class ApplicationUtil {

    private static Context context;

    public static void init(Context context)
    {
        if(context == null)
            throw new IllegalArgumentException("context cannot be null!");
        ApplicationUtil.context = context;
    }

    public static Context getContext()
    {
        if(context == null)
            throw new IllegalArgumentException("context cannot be null!");
        return context;
    }
}

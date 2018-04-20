package com.liurui.common.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.support.v4.app.AppOpsManagerCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by LiuRui on 2018/4/17.
 */

public class PermissionUtil {


    public static boolean checkPermission(Context context, String permission) {
        boolean result = false;
        if (context == null) {
            return result;
        }
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(context,permission)) {
                    result = true;
                } else {
                    result = false;
                }
            } catch (Throwable e) {
                result = false;
            }
        } else {
            PackageManager pm = context.getPackageManager();
            if (pm.checkPermission(permission, context.getPackageName()) == PackageManager.PERMISSION_GRANTED) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 查询小米手机的权限状态
     * @param context
     * @param permissionName
     * @return
     */
    public static boolean isPermissionGrantedInMIUI(Context context, String permissionName) {
        final String opName = AppOpsManagerCompat.permissionToOp(permissionName);
        if (opName == null) {
            return true;
        } else {
            int mode = AppOpsManagerCompat.noteOp(context,
                    opName, Process.myUid(), context.getPackageName());
            return mode == AppOpsManagerCompat.MODE_ALLOWED;
        }
    }
}

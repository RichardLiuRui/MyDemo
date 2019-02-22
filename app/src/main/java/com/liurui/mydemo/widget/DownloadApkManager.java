package com.liurui.mydemo.widget;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import com.liurui.common.utils.ApplicationUtil;

import java.io.File;

/**
 * Created by LiuRui on 2018/12/25
 */
public class DownloadApkManager {

    public static void downloadApk(String downUrl, String fileName, String filePath) {
        //取得系统的下载服务
        DownloadManager downloadManager = (DownloadManager) ApplicationUtil.getContext().getSystemService(Context.DOWNLOAD_SERVICE);
        //创建下载请求对象
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downUrl));
        request.setTitle(fileName);
        request.setDescription("正在下载...");
        request.setMimeType("application/vnd.android.package-archive");
        request.allowScanningByMediaScanner();// 设置可以被扫描到;在默认的情况下，通过Download
        // Manager下载的文件是不能被Media Scanner扫描到的
        request.setVisibleInDownloadsUi(true);// 设置下载可见
        request.setAllowedOverRoaming(false);//可以用来阻止手机在漫游状态下下载
        request.setDestinationUri(Uri.fromFile(new File(filePath + File.separator + fileName)));
        request.setNotificationVisibility(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request
                .VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);
    }

}

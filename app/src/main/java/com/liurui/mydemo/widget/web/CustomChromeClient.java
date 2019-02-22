package com.liurui.mydemo.widget.web;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

/**
 * WebChromeClient主要辅助WebView处理Javascript的对话框、网站图标、网站title、加载进度等
 * Created by LiuRui on 2018/12/13
 */
public class CustomChromeClient extends WebChromeClient {

    private ProgressBar progressBar;
    private OnChromClientListener onChromClientListener;

    public CustomChromeClient(ProgressBar progressBar, OnChromClientListener onChromClientListener) {
        this.onChromClientListener = onChromClientListener;
        this.progressBar = progressBar;
    }

    //不支持js的alert弹窗，需要自己监听然后通过dialog弹窗
    @Override
    public boolean onJsAlert(WebView webView, String url, String message, JsResult result) {
//        AlertDialog.Builder localBuilder = new AlertDialog.Builder(webView.getContext());
//        localBuilder.setMessage(message).setPositiveButton("确定",null);
//        localBuilder.setCancelable(false);
//        localBuilder.create().show();

        //注意:
        //必须要这一句代码:result.confirm()表示:
        //处理结果为确定状态同时唤醒WebCore线程
        //否则不能继续点击按钮
        result.confirm();
        return true;
    }

    //获取网页标题
    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (onChromClientListener != null){
            onChromClientListener.onUpdateTitle(title);
        }
    }

    //加载进度回调
    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        progressBar.setProgress(newProgress);
    }

    public interface OnChromClientListener{
        void onUpdateTitle(String title);
    }
}

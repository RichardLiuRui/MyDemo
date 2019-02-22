package com.liurui.mydemo.widget.web;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

/**
 * WebViewClient主要帮助WebView处理各种通知、请求事件
 * Created by LiuRui on 2018/12/13
 */
public class CustomWebViewClient extends WebViewClient {

    private ProgressBar progressBar;

    public CustomWebViewClient(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void onPageFinished(WebView view, String url) {//页面加载完成
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i("ansen","拦截url:"+url);
        if(url.equals("http://www.google.com/")){
            return true;//表示我已经处理过了
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        //super.onReceivedSslError(view, handler, error);
        handler.proceed(); // 接受所有网站的证书
    }
}

package com.liurui.mydemo;

import android.app.Application;

import com.liurui.common.utils.ApplicationUtil;
import com.liurui.common.utils.GsonUtil;
import com.liurui.data.DefaultResponse;
import com.liurui.data.DefaultResponseDeserializer;
import com.liurui.data.repository.datasource.NetWorkInterceptorConfig;
import com.liurui.mydemo.framework.NetWorkInterceptor;
import com.liurui.mydemo.widget.CrashHandler;
import com.liurui.mydemo.widget.Foreground;
import com.liurui.mydemo.widget.SharedPreferenceUtil;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by LiuRui on 2018/4/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Foreground.init(this);

        ApplicationUtil.init(this);

        CrashHandler.getInstance().init(this);

        SharedPreferenceUtil.getInstance(this, "MY_CONFIG");

        GsonUtil.registerTypeAdapter(DefaultResponse.class, new DefaultResponseDeserializer());

        Interceptor interceptor = null;
        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor it = new HttpLoggingInterceptor();
            it.setLevel(HttpLoggingInterceptor.Level.BODY);
            interceptor = it;
        }
        NetWorkInterceptorConfig.init(new NetWorkInterceptor(),interceptor);
    }
}

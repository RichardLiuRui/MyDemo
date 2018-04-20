package com.liurui.mydemo;

import android.app.Application;

import com.liurui.common.utils.ApplicationUtil;
import com.liurui.common.utils.GsonUtil;
import com.liurui.data.DefaultResponse;
import com.liurui.data.DefaultResponseDeserializer;
import com.liurui.data.repository.datasource.NetWorkInterceptorConfig;
import com.liurui.mydemo.framework.NetWorkInterceptor;

import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by LiuRui on 2018/4/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApplicationUtil.init(this);

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

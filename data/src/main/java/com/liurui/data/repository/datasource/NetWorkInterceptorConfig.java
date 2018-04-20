package com.liurui.data.repository.datasource;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class NetWorkInterceptorConfig {

    private static Interceptor netWorkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request());
        }
    };
    private static Interceptor logInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            return chain.proceed(chain.request());
        }
    };
    public static void init(Interceptor netWorkInterceptor,Interceptor logInterceptor ){
        if(netWorkInterceptor != null) {
            NetWorkInterceptorConfig.netWorkInterceptor = netWorkInterceptor;
        }
        if(logInterceptor != null) {
            NetWorkInterceptorConfig.logInterceptor = logInterceptor;
        }
    }
    public static Interceptor getNetWorkInterceptor(){
        return  netWorkInterceptor;
    }

    public static Interceptor getLogInterceptor() {
        return logInterceptor;
    }

}

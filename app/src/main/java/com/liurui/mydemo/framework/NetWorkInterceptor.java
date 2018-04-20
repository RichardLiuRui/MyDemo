package com.liurui.mydemo.framework;

import android.text.TextUtils;

import com.liurui.common.assist.Logger;
import com.liurui.common.utils.AppUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class NetWorkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
//        if (!LocalStoreManager.isLogin()) {
//            return chain.proceed(originalRequest);
//        }
//        String lat = LocalStoreManager.getCurrentLatitude();
//        String lng = LocalStoreManager.getCurrentLontitude();

//        System.out.println(lat+"====="+lng);
        Request authorised = originalRequest.newBuilder()
//                .addHeader("token", LocalStoreManager.getUserToken())
//                .addHeader("phone", LocalStoreManager.getUserMobile())
                .addHeader("version", AppUtil.getAppVersion() )
                .addHeader("type","android")
//                .addHeader("uLat", TextUtils.isEmpty(lat)?"":lat)
//                .addHeader("uLng",TextUtils.isEmpty(lng)?"":lng)
                .build();
        Logger.log(Logger.SCOPE.NETWORK,"## request url :"+originalRequest.url().toString());
//        Logger.log(Logger.SCOPE.NETWORK,"## request header token:"+LocalStoreManager.getUserToken());
//        Logger.log(Logger.SCOPE.NETWORK,"## request header phone:"+LocalStoreManager.getUserMobile());
        Logger.log(Logger.SCOPE.NETWORK,"## request header version:"+AppUtil.getAppVersion());


        return chain.proceed(authorised);
    }
}

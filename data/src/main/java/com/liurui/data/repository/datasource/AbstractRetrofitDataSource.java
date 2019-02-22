package com.liurui.data.repository.datasource;

import com.liurui.common.utils.GsonUtil;
import com.liurui.data.api.Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by LiuRui on 2018/4/18.
 */

public abstract class AbstractRetrofitDataSource {

    Api api;
    Retrofit retrofit;

    public AbstractRetrofitDataSource() {
        retrofit = getRetrofitInstance(true);
        api = retrofit.create(Api.class);
    }

    Retrofit getRetrofitInstance(boolean userRxJava){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(getApiUrl())
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()));
        if(userRxJava){
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        builder.client(getClient());
        return builder.build();
    }

    OkHttpClient getClient(){
        return new OkHttpClient.Builder()
                // HeadInterceptor实现了Interceptor，用来往Request Header添加一些业务相关数据，如APP版本，token信息
                .addInterceptor(NetWorkInterceptorConfig.getLogInterceptor())
                .addInterceptor(NetWorkInterceptorConfig.getNetWorkInterceptor())
                // 连接超时时间设置
                .connectTimeout(10, TimeUnit.SECONDS)
                // 读取超时时间设置
                .readTimeout(10, TimeUnit.SECONDS)
//                .sslSocketFactory(sslSocketFactory)
                // 信任所有主机名
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                // 这里我们使用host name作为cookie保存的key
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.host()), cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(HttpUrl.parse(url.host()));
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();
    }

    public RequestBody createRequestBody(Map<String,Object> map){
        String json = GsonUtil.toGson(map);//要传递的json
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
    }

    //创建表单的普通字段
    public static RequestBody createFormBody(String content) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), content);
    }

    abstract String getApiUrl();
}

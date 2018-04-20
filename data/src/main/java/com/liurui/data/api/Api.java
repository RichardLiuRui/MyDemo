package com.liurui.data.api;

import com.liurui.data.DefaultResponse;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by LiuRui on 2018/4/17.
 */

public interface Api {

    String BASE_URL = "http://www.wanandroid.com/";

    String GET_ARTICLE_LIST = "article/list/{page}/json";

    @GET(GET_ARTICLE_LIST)
    Observable<DefaultResponse> getArtiCleList(@Path("page") int page);

}

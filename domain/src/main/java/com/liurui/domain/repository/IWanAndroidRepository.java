package com.liurui.domain.repository;

import com.liurui.domain.beans.ArticleList;
import com.liurui.domain.interactor.RequestParameter;

import io.reactivex.Observable;


/**
 * Created by LiuRui on 2018/4/18.
 */

public interface IWanAndroidRepository {

    Observable<ArticleList> getArticleList(RequestParameter parameter);
}

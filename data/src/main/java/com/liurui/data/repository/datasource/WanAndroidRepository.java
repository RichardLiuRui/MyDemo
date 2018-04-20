package com.liurui.data.repository.datasource;

import com.liurui.domain.beans.ArticleList;
import com.liurui.domain.interactor.RequestParameter;
import com.liurui.domain.repository.IWanAndroidRepository;

import io.reactivex.Observable;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class WanAndroidRepository implements IWanAndroidRepository {

    private WanAndroidFactory wanAndroidFactory;

    public WanAndroidRepository() {
        wanAndroidFactory = new WanAndroidFactory();
    }

    @Override
    public Observable<ArticleList> getArticleList(RequestParameter parameter) {
        return wanAndroidFactory.getWanAndroidDataSource().getArticleList(parameter);
    }
}

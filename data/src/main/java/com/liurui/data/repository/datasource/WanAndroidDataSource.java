package com.liurui.data.repository.datasource;

import com.liurui.data.repository.RepositoryUtils;
import com.liurui.domain.beans.ArticleList;
import com.liurui.domain.interactor.RequestParameter;

import io.reactivex.Observable;


/**
 * Created by LiuRui on 2018/4/18.
 */

public class WanAndroidDataSource extends DefaultDataSource implements IWanAndroidDataSource {

    @Override
    public Observable<ArticleList> getArticleList(RequestParameter requestParameter) {
        return RepositoryUtils.extractData(api.getArtiCleList(Integer.parseInt(requestParameter.getParams().get("page"))), ArticleList.class);
    }
}

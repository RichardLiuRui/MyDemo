package com.liurui.domain.interactor.usecase;

import com.liurui.domain.interactor.RequestParameter;
import com.liurui.domain.repository.IWanAndroidRepository;

import io.reactivex.Observable;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class GetArticleListUseCase extends DefaultRxUseCase {


    private String page;
    private IWanAndroidRepository repository;

    public GetArticleListUseCase(IWanAndroidRepository repository) {
        this.repository = repository;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    protected RequestParameter buildParameter() {
        DefaultRequestParameter parameter = new DefaultRequestParameter();
        parameter.put("page", getPage());
        return parameter;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getArticleList(buildParameter());
    }
}

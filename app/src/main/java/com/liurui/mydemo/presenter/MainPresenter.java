package com.liurui.mydemo.presenter;

import com.liurui.common.assist.Logger;
import com.liurui.data.repository.datasource.WanAndroidRepository;
import com.liurui.domain.interactor.usecase.GetArticleListUseCase;
import com.liurui.mydemo.view.MainView;
import com.nodescm.app.mvp.base.MvpBasePresenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class MainPresenter extends MvpBasePresenter<MainView> implements IMainPresenter{

    private Disposable getArticleListDisposable;

    @Override
    public void viewInited() {
        super.viewInited();
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        if (getArticleListDisposable != null && !getArticleListDisposable.isDisposed()){
            getArticleListDisposable.dispose();
        }
    }

    @Override
    public void getArticleList(){
        if (isViewAttached()){
            GetArticleListUseCase getArticleListUseCase = new GetArticleListUseCase(new WanAndroidRepository());
            getArticleListUseCase.setPage("1");
            getArticleListUseCase.execute(getArticleListObserver);
        }
    }

    private Observer getArticleListObserver = new Observer() {
        @Override
        public void onSubscribe(Disposable d) {
            getArticleListDisposable = d;
        }

        @Override
        public void onNext(Object o) {
            Logger.log("getArticleList onNext", "onNext");
        }

        @Override
        public void onError(Throwable e) {
            Logger.log("getArticleList onError", e.getMessage());
        }

        @Override
        public void onComplete() {
            Logger.log("getArticleList onComplete", "complete");
        }
    };
}

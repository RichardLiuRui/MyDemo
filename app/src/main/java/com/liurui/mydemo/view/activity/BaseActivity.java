package com.liurui.mydemo.view.activity;

import android.content.Context;
import android.os.Bundle;

import com.nodescm.app.mvp.MvpActivity;
import com.nodescm.app.mvp.base.MvpPresenter;
import com.nodescm.app.mvp.base.MvpView;

/**
 * Created by LiuRui on 2018/4/17.
 */

public abstract class BaseActivity <V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V,P> {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected int setContainerId() {
        return 0;
    }

}

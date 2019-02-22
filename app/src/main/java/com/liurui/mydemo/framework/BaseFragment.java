package com.liurui.mydemo.framework;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.liurui.common.utils.ApplicationUtil;
import com.liurui.mydemo.util.CommonUtil;
import com.liurui.mydemo.R;
import com.liurui.mydemo.view.dialog.LoadingDialog;
import com.liurui.mydemo.widget.glide.GlideUtil;
import com.nodescm.app.mvp.MvpFragment;
import com.nodescm.app.mvp.base.MvpPresenter;
import com.nodescm.app.mvp.base.MvpView;

/**
 * Created by LiuRui on 2018/11/21
 */
public abstract class BaseFragment<V extends MvpView, P extends MvpPresenter<V>> extends MvpFragment<V,P> {

    private LoadingDialog waitingDialog;
    protected boolean prepared= false;
    public boolean dataLoaded= false;
    public boolean isViewCreated = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isViewCreated = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepared = true;
    }

    @Override
    public void onStart() {
        super.onStart();
        if(getUserVisibleHint()) {
            lazyLoad();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        prepared = false;
        dataLoaded = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (waitingDialog != null){
            waitingDialog.hide();
        }
    }

    public void showWaiting(){
        if (waitingDialog == null){
            waitingDialog = new LoadingDialog(_mActivity);
        }
        if (!waitingDialog.isShowing()){
            waitingDialog.show();
        }
    }

    public void hideWaitiog(){
        if (waitingDialog != null && waitingDialog.isShowing()){
            waitingDialog.dismiss();
            waitingDialog = null;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d("TAG",  " setUserVisibleHint() --> isVisibleToUser = " + isVisibleToUser);

        if (isVisibleToUser && isViewCreated) {
            lazyLoad();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void lazyLoad() {
        if (!dataLoaded) {
            dataLoaded = true;
            loadData();
        }
    }

    public abstract void loadData();

    public RelativeLayout initCommonHeader() {
        RelativeLayout headerLayout = new RelativeLayout(_mActivity);
        headerLayout.setBackgroundResource(R.color.trans);
        RelativeLayout.LayoutParams headerLayoutLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.INSTANCE.dp2px(ApplicationUtil.getContext(), 60F));
        headerLayout.setLayoutParams(headerLayoutLP);

        ImageView headerImg = new ImageView(_mActivity);
        RelativeLayout.LayoutParams imageLp = new RelativeLayout.LayoutParams(CommonUtil.INSTANCE.dp2px(_mActivity, 70F),
                CommonUtil.INSTANCE.dp2px(_mActivity, 30F));
        imageLp.addRule(RelativeLayout.CENTER_IN_PARENT);
        headerImg.setLayoutParams(imageLp);
        GlideUtil.glideLoadGifImage(_mActivity, R.mipmap.gif_header, headerImg);
        headerLayout.addView(headerImg);

        return headerLayout;
    }

    public RelativeLayout initChangingColorHeader() {
        RelativeLayout headerLayout = new RelativeLayout(_mActivity);
        RelativeLayout.LayoutParams headerLayoutLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.INSTANCE.dp2px(_mActivity, 60F));
        headerLayout.setLayoutParams(headerLayoutLP);
        headerLayout.setBackgroundResource(R.drawable.bg_navi_bar);

        return headerLayout;
    }

}

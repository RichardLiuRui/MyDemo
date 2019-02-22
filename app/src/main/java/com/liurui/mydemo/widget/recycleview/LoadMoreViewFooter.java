package com.liurui.mydemo.widget.recycleview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chanven.lib.cptr.loadmore.ILoadMoreViewFactory;
import com.liurui.common.utils.ApplicationUtil;
import com.liurui.mydemo.R;
import com.liurui.mydemo.widget.glide.GlideUtil;

/**
 * Created by LiuRui on 2018/12/4
 */
public class LoadMoreViewFooter implements ILoadMoreViewFactory {
    @Override
    public ILoadMoreView madeLoadMoreView() {
        return new LoadMoreHelper();
    }

    private class LoadMoreHelper implements ILoadMoreView {

        protected View footerView;
        protected TextView footerTv;
        protected ImageView footerImage;

        protected View.OnClickListener onClickRefreshListener;

        @Override
        public void init(ILoadMoreViewFactory.FootViewAdder footViewHolder, View.OnClickListener onClickRefreshListener) {
            footerView = footViewHolder.addFootView(R.layout.layout_loadmore_footer);
            footerTv = (TextView) footerView.findViewById(R.id.loadmore_footer_tv);
            footerImage = (ImageView) footerView.findViewById(R.id.loadmore_footer_image);
            GlideUtil.glideLoadGifImage(ApplicationUtil.getContext(), R.mipmap.gif_header, footerImage);
            this.onClickRefreshListener = onClickRefreshListener;
            showNormal();
        }

        @Override
        public void showNormal() {
            footerTv.setText("点击加载更多");
            footerImage.setVisibility(View.GONE);
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showLoading() {
            footerTv.setText("正在加载中...");
            footerImage.setVisibility(View.VISIBLE);
            footerView.setOnClickListener(null);
        }

        @Override
        public void showFail(Exception exception) {
            footerTv.setText("加载失败，点击重新");
            footerImage.setVisibility(View.GONE);
            footerView.setOnClickListener(onClickRefreshListener);
        }

        @Override
        public void showNomore() {
            footerTv.setText("已经加载完毕");
            footerImage.setVisibility(View.GONE);
            footerView.setOnClickListener(null);
        }

        @Override
        public void setFooterVisibility(boolean isVisible) {
            footerView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }
}

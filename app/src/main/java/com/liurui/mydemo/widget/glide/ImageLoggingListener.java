package com.liurui.mydemo.widget.glide;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

/**
 * Created by LiuRui on 2018/12/12
 */
public class ImageLoggingListener<T,R> implements RequestListener<T,R> {
    @Override
    public boolean onException(Exception e, T model, Target<R> target, boolean isFirstResource) {
        return false;
    }

    @Override
    public boolean onResourceReady(R resource, T model, Target<R> target, boolean isFromMemoryCache, boolean isFirstResource) {
        return false;
    }
}

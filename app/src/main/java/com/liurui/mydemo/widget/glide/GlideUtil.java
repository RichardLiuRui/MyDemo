package com.liurui.mydemo.widget.glide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.EmptySignature;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.liurui.common.utils.ApplicationUtil;
import com.liurui.common.utils.FileUtil;
import com.liurui.mydemo.view.customview.RoundImage;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.concurrent.ExecutionException;

/**
 * Created by LiuRui on 2018/11/21
 */
public class GlideUtil {
    private static RequestListener listener = new ImageLoggingListener();

    public static void savImageByBytes(final Context context, final String url) {
        new Thread() {
            @Override
            public void run() {
                try {
                    File glideFile = Glide.with(context)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    String fileName = glideFile.getName() + FileUtil.IMG_SUFFIX;
                    FileUtil.copyFile(glideFile.getPath(), FileUtil.IMG_DOWNLOAD_DIR, fileName);
                    ApplicationUtil.getContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(FileUtil.IMG_DOWNLOAD_DIR + File.separator + fileName))));
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public static void loadImageBitmap(final Context context, String url, final SubsamplingScaleImageView imgView) {
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imgView.setImage(ImageSource.bitmap(resource));
            }
        }); //方法中设置asBitmap可以设置回调类型
    }

    public static void loadCircleImage(Context context, String url, ImageView imgView) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(listener)
                .transform(new GlideCircleTransform(context))
                .into(imgView);
    }

    public static void glideLoadImage(Context context, String url, ImageView imageView, int width, int height, Drawable placeholde, boolean ifUseBitMap) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        if (ifUseBitMap) {
            if (width > 0 && height > 0) {
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .fitCenter()
                        .override(width, height)
                        .skipMemoryCache(false)
                        .placeholder(placeholde)
                        .into(imageView);
            } else {
                Glide.with(context)
                        .load(url)
                        .asBitmap()
                        .fitCenter()
                        .skipMemoryCache(false)
                        .placeholder(placeholde)
                        .into(imageView);
            }
        } else {
            if (width > 0 && height > 0) {
                Glide.with(context)
                        .load(url)
                        .fitCenter()
                        .override(width, height)
                        .skipMemoryCache(false)
                        .placeholder(placeholde)
                        .into(imageView);
            } else {
                Glide.with(context)
                        .load(url)
                        .fitCenter()
                        //.override(width,height)
                        .skipMemoryCache(false)
                        .placeholder(placeholde)
                        .into(imageView);
            }
        }
    }

    public static void glideLoadImage(Context context, String url, ImageView imageView, @DrawableRes int placeholde, boolean ifUseBitMap) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        if (ifUseBitMap) {
            Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .fitCenter()
                    .skipMemoryCache(false)
                    .placeholder(placeholde)
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(url)
                    .fitCenter()
                    .skipMemoryCache(false)
                    .placeholder(placeholde)
                    .into(imageView);
        }
    }

    public static void glideLoadImage(Context context, String url, ImageView imageView, @DrawableRes int placeHolder) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(placeHolder)
                .skipMemoryCache(false)
                .into(imageView);
    }

    public static void glideLoadImage(Context context, String url, ImageView imageView) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop()
                .skipMemoryCache(false)
                .into(imageView);
    }

    public static void glideLoadImage(Context context, String url, RoundImage imageView, @DrawableRes int placeHolder) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(placeHolder)
                .skipMemoryCache(false)
                .into(imageView);
    }

    public static void glideLoadImageFitCenter(Context context, String url, ImageView imageView) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        Glide.with(context)
                .load(url)
                .fitCenter()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static void glideLoadImageFitCenter(Context context, String url, ImageView imageView, @DrawableRes int placeHolder) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        Glide.with(context)
                .load(url)
                .fitCenter()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void glideLoadGifImage(Context context, int url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }


    public static void glideLoadUri(Context context, Uri uri, ImageView imageView) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        Glide.with(context)
                .load(uri.getPath())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .centerCrop()
                .into(imageView);
    }

    public static void glideLoadDrawale(Context context, Drawable drawable, ImageView imageView) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        Glide.with(context)
                .load("")
                .asBitmap()
                .placeholder(drawable)
                .centerCrop()
                .into(imageView);
    }

    public static void glideLoadResource(Context context, int id, ImageView imageView) {
        Glide.with(context)
                .load(id)
                .centerCrop()
                .into(imageView);
    }

    public static void glideLoadImageWithCenterCrop(Context context, String url, ImageView imageView, Drawable placeholder) {
        if (context == null || !(context instanceof Activity) || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && ((Activity) context).isDestroyed()))
            return;
        Glide.with(context)
                .load(url)
                .placeholder(placeholder)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

    public static File imageHasloadCache(Context context, String url) {
        // 寻找缓存图片
        return DiskLruCacheWrapper.get(Glide.getPhotoCacheDir(context), 250 * 1024 * 1024).get(new OriginalKey(url, EmptySignature.obtain()));
    }


    // 清除图片磁盘缓存，调用Glide自带方法
    public static boolean clearCacheDiskSelf() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(ApplicationUtil.getContext()).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(ApplicationUtil.getContext()).clearDiskCache();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 清除Glide内存缓存
    public static boolean clearCacheMemory() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(ApplicationUtil.getContext()).clearMemory();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Glide原图缓存Key
     */
    private static class OriginalKey implements Key {

        private final String id;
        private final Key signature;

        private OriginalKey(String id, Key signature) {
            this.id = id;
            this.signature = signature;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            OriginalKey that = (OriginalKey) o;

            return id.equals(that.id) && signature.equals(that.signature);
        }

        @Override
        public int hashCode() {
            int result = id.hashCode();
            result = 31 * result + signature.hashCode();
            return result;
        }

        @Override
        public void updateDiskCacheKey(MessageDigest messageDigest) throws UnsupportedEncodingException {
            messageDigest.update(id.getBytes(STRING_CHARSET_NAME));
            signature.updateDiskCacheKey(messageDigest);
        }
    }


}

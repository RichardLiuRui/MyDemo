package com.liurui.mydemo.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.liurui.mydemo.R;


/**
 * Created by LiuRui on 2018/11/21
 */
public class LoadingDialog extends Dialog {

    private Context mContext;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.loading_dialog);
        mContext = context;
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        super(context);
        mContext = context;
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_waiting);

        init();
    }

    private void init(){
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        ImageView waitIcon = findViewById(R.id.dialog_wait_icon);

        Animation loadingAnimation = AnimationUtils.loadAnimation(mContext,
                R.anim.anim_loading);
        waitIcon.startAnimation(loadingAnimation);
    }

}

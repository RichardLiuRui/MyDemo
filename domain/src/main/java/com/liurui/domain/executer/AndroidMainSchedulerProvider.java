package com.liurui.domain.executer;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class AndroidMainSchedulerProvider implements SchedulerProvider {

    private static class AndroidMainSchedulerProviderHolder{
        private static final AndroidMainSchedulerProvider INSTANCE = new AndroidMainSchedulerProvider();
    }

    private AndroidMainSchedulerProvider(){}

    public static AndroidMainSchedulerProvider getInstance(){
        return AndroidMainSchedulerProviderHolder.INSTANCE;
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}

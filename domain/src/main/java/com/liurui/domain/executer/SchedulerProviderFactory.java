package com.liurui.domain.executer;

/**
 * Created by LiuRui on 2018/4/18.
 */

public enum SchedulerProviderFactory {
    INSTANCE;

    SchedulerProviderFactory(){}

    public SchedulerProvider createAndroidMainSchedulerProvider(){
        SchedulerProvider provider = AndroidMainSchedulerProvider.getInstance();
        return provider;
    }

    public SchedulerProvider createCommonSchedulerProvider(){
        SchedulerProvider provider = CommonSchedulerProvider.getInstance();
        return provider;
    }

}

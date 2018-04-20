package com.liurui.domain.executer;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class CommonSchedulerProvider implements SchedulerProvider {

    private CommonSchedulerProvider(){}

    private static class CommonSchedulerProviderHolder{
        private static final CommonSchedulerProvider INSTANCE = new CommonSchedulerProvider();
    }

    public static CommonSchedulerProvider getInstance(){
        return CommonSchedulerProviderHolder.INSTANCE;
    }

    @Override
    public Scheduler getScheduler() {
        return Schedulers.from(new JobExecutor());
    }
}

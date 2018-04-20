package com.liurui.domain.interactor.usecase;

import com.liurui.domain.executer.SchedulerProviderFactory;
import com.liurui.domain.interactor.RequestParameter;

import io.reactivex.Scheduler;

/**
 * Created by LiuRui on 2018/4/18.
 */

public abstract class DefaultRxUseCase extends UseCase {

    protected DefaultRxUseCase(){
        super(SchedulerProviderFactory.INSTANCE.createCommonSchedulerProvider().getScheduler(),SchedulerProviderFactory.INSTANCE.createAndroidMainSchedulerProvider().getScheduler());
    }

    protected abstract RequestParameter buildParameter();


}

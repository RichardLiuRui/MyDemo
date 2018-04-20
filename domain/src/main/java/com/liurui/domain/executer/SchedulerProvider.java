package com.liurui.domain.executer;

import io.reactivex.Scheduler;

/**
 * Created by LiuRui on 2018/4/18.
 */

public interface SchedulerProvider {
    Scheduler getScheduler();
}

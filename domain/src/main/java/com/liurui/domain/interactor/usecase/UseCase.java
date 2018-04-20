package com.liurui.domain.interactor.usecase;




import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * Created by LiuRui on 2018/4/18.
 */

public abstract class UseCase {

    private final Scheduler threadExecutorScheduler;
    private final Scheduler postExecutionThreadScheduler;



    protected UseCase(Scheduler threadExecutor,
                      Scheduler postExecutionThreadScheduler) {
        this.threadExecutorScheduler = threadExecutor;
        this.postExecutionThreadScheduler = postExecutionThreadScheduler;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable buildUseCaseObservable();



    /**
     * Executes the current use case.
     *
     * @param UseCaseSubscriber The guy who will be listen to the observable build
     * with {@link #buildUseCaseObservable()}.
     */
    public void execute(Observer UseCaseSubscriber) {
        this.buildUseCaseObservable()
                .subscribeOn(threadExecutorScheduler)
                .observeOn(postExecutionThreadScheduler)
                .subscribe(UseCaseSubscriber);
    }

//    /**
//     * Unsubscribes from current {@link Subscription}.
//     */
//    public void unsubscribe() {
//        if (!disposable.isUnsubscribed()) {
//            disposable.unsubscribe();
//        }
//    }

}

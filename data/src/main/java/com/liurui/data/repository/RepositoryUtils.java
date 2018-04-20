package com.liurui.data.repository;

import com.liurui.common.assist.Logger;
import com.liurui.common.utils.GsonUtil;
import com.liurui.data.DefaultResponse;
import com.liurui.data.exception.DefaultException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class RepositoryUtils {

    public static <T> Observable<T> extractData(Observable<DefaultResponse> observable, final Class<T> clazz) {
        return observable.flatMap(new Function<DefaultResponse, ObservableSource<? extends T>>() {
            @Override
            public ObservableSource<? extends T> apply(DefaultResponse defaultResponse) throws Exception {
                if (defaultResponse == null) {
                    Logger.log(Logger.SCOPE.NETWORK,"response is null");
                    return Observable.error(DefaultException.networkError("response is null"));
                } else if (defaultResponse.getErrorCode() >= DefaultResponse.CODE_OK ) {
                    return Observable.just(GsonUtil.fromGson(defaultResponse.getData(), clazz));
                } else {
                    Logger.log(Logger.SCOPE.NETWORK,defaultResponse.getErrorCode()+"##"+defaultResponse.getErrorMsg());
                    return Observable.error(DefaultException.serverResponseError(defaultResponse));
                }
            }
        });
    }
}

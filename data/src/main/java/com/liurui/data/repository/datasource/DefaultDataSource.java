package com.liurui.data.repository.datasource;

import com.liurui.data.api.Api;

/**
 * Created by LiuRui on 2018/4/18.
 */

public abstract class DefaultDataSource extends AbstractRetrofitDataSource {

    @Override
    public String getApiUrl() {
        return Api.BASE_URL;
    }

}

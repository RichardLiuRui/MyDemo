package com.liurui.data.repository.datasource;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class WanAndroidFactory {
    WanAndroidDataSource wanAndroidDataSource;

    public WanAndroidFactory() {
    }

    /**
     * Create {@link WanAndroidDataSource} from a user id.
     */
    public WanAndroidDataSource create() {

        wanAndroidDataSource = new WanAndroidDataSource();

        return wanAndroidDataSource;
    }


    /**
     * Create {@link WanAndroidDataSource} to retrieve data from the Cloud.
     */
    public WanAndroidDataSource getWanAndroidDataSource() {

        if(wanAndroidDataSource == null){
            create();
        }
        return wanAndroidDataSource;
    }
}

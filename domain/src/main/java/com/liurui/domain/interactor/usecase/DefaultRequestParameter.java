package com.liurui.domain.interactor.usecase;

import com.liurui.domain.interactor.RequestParameter;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class DefaultRequestParameter implements RequestParameter {
    private TreeMap<String, String> params = new TreeMap<>();
    public DefaultRequestParameter(){

    }
    public DefaultRequestParameter(TreeMap<String, String> params) {
        if (params != null) {
            this.params.putAll(params);
        }
    }
    public void put(String key,String value){
        this.params.put(key,value);
    }

    @Override
    public TreeMap<String, String> getParams() {
        return params;
    }

    public void clear()
    {
        params.clear();
    }

    public void remove(String key){
        params.remove(key);
    }
}

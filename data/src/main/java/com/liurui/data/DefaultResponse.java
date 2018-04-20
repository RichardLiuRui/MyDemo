package com.liurui.data;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class DefaultResponse {

    public static final int CODE_OK = 0;
    public static final int CODE_ERROR = -1;

    private int errorCode;

    private String data;

    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}

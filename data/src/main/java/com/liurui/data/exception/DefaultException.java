package com.liurui.data.exception;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.liurui.data.DefaultResponse;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class DefaultException extends RuntimeException {

    public static Exception httpError(@NonNull final String psUrl, @NonNull final Response poResponse) {
        final String lsMessage = poResponse.code() + " " + poResponse.message();
        return new DefaultException(lsMessage, psUrl, Kind.HTTP, null,null,poResponse.code());
    }

    public static DefaultException httpError(@NonNull final String psUrl, @NonNull final String msg,@NonNull final int code) {
        return new DefaultException(msg, psUrl, Kind.HTTP, null,null,code);
    }


    public static DefaultException networkError(@NonNull final IOException poException) {
        return new DefaultException(poException.getMessage(), null,  Kind.NETWORK, null,poException, DefaultResponse.CODE_ERROR);
    }

    public static DefaultException networkError(@NonNull final String errorMsg) {
        return new DefaultException(errorMsg, null, Kind.NETWORK, null,null, DefaultResponse.CODE_ERROR);
    }


    public static DefaultException serverResponseError(@NonNull final DefaultResponse response) {
        return new DefaultException(response.getErrorMsg(), null, Kind.SERVER, response,null, response.getErrorCode());
    }


    public static DefaultException unexpectedError(@NonNull final Throwable poException) {
        return new DefaultException(poException.getMessage(), null, Kind.UNEXPECTED, null,poException,Integer.MAX_VALUE);
    }

    /**
     * Identifies the event kind which triggered a {@link Exception}.
     */
    public enum Kind {
        /**
         * An {@link IOException} occurred while communicating to the server.
         */
        NETWORK,
        /**
         * A non-200 HTTP status code was received from the server.
         */
        HTTP,

        /**
         * server response error
         */

        SERVER,
        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        UNEXPECTED
    }


    private final String mUrl;
    private final Kind mKind;
    private final int errorCode;
    private final DefaultResponse response;

    DefaultException(@NonNull final String psMessage,
                     @Nullable final String psUrl,
                     @NonNull final Kind poKind,
                     @Nullable final DefaultResponse response,
                     @Nullable final Throwable poException,
                     @Nullable final int errCode) {
        super(psMessage, poException);
        mUrl = psUrl;
        mKind = poKind;
        errorCode = errCode;
        this.response = response;
    }


    public DefaultResponse getResponse() {
        return response;
    }

    public String getmUrl() {
        return mUrl;
    }

    public Kind getmKind() {
        return mKind;
    }

    public int getErrorCode() {
        return errorCode;
    }



    public static final int ERROR_CODE_NEED_LOGIN = 2;
    public static final int ERROR_CODE_NEED_COMPLETE_PROFILE = 4;

}

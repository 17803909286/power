package com.power.home.net.rto_exception;

/**
 * Created by ZHP on 2017/6/26.
 */

public class ApiException extends BaseException {
    public ApiException(String code, String displayMessage) {
        super(code, displayMessage);
    }
}

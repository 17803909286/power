package com.power.home.net.rto_exception;

/**
 * Created by ZHP on 2017/6/26.
 */

public class BaseException extends Exception {

    public static final String GO_LOGIN = "10401";
    public static final String ERROR_TOKEN = "10403";
    public static final String NOT_LOGIN = "10403";

    public static final int ERROR_AMOUNT = 10002;


    private String code;

    private String displayMessage;

    public BaseException() {
    }

    public BaseException(String code, String displayMessage) {
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public BaseException(String message, String code, String displayMessage) {
        super(message);
        this.code = code;
        this.displayMessage = displayMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}

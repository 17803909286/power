package com.power.home.data.bean;


import com.power.home.data.Optional;

/**
 * Created by ZHP on 2017/6/24.
 */

public class BaseBean<T> extends BaseEntity {

    public static final String SUCCESS = "0";
    private String code;

    private String msg;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean success() {

        return (code.equals(SUCCESS));
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Optional<T> transform() {
        return new Optional<>(data);
    }

}

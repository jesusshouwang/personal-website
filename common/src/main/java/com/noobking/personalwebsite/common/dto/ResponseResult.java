package com.noobking.personalwebsite.common.dto;

import java.io.Serializable;

/**
 * 统一的响应回复
 * @param <T>
 */
public class ResponseResult<T> implements Serializable {
    //状态码
    private int code;
    //信息
    private String message;
    //数据
    private T data;

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

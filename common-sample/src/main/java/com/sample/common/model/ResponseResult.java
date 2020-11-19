package com.sample.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ResponseResult<T> {

    private String code;

    private String msg;

    private T data;

    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult success() {
        return new ResponseResult(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(),"");
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult(ResponseCodeEnum.SUCCESS.getCode(), ResponseCodeEnum.SUCCESS.getMessage(), data);
    }

    public static ResponseResult error(ResponseCodeEnum responseCodeEnum) {
        return new ResponseResult(responseCodeEnum.getCode(), responseCodeEnum.getMessage(),"");
    }
    public static <T> ResponseResult<T> error(ResponseCodeEnum responseCodeEnum,T data) {
        return new ResponseResult(responseCodeEnum.getCode(), responseCodeEnum.getMessage(),data);
    }
    public static ResponseResult error(String code, String msg) {
        return new ResponseResult(code, msg,"");
    }

    public static <T> ResponseResult<T> error(String code, String msg, T data) {
        return new ResponseResult(code, msg, data);
    }
    @JsonIgnore
    public boolean isSuccess() {
        return "0".equals(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

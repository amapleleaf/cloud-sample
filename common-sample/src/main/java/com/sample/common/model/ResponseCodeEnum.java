package com.sample.common.model;

public enum ResponseCodeEnum {

    SUCCESS("0", "成功"),
    FAIL("-1", "系统异常"),
    PARAM_ERROR("10000", "传入参数错误"),
    TOKEN_MISSION("10001", "token缺失"),
    TOKEN_INVALID("10002", "无效的Token"),
    TOKEN_EXPIRED("10004", "token已过期");

    private String code;

    private String message;

    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
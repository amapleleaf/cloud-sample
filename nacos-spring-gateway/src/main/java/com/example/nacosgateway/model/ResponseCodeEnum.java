package com.example.nacosgateway.model;

public enum ResponseCodeEnum {

    SUCCESS(0, "成功"),
    FAIL(-1, "失败"),
    TOKEN_MISSION(10001, "token缺失"),
    TOKEN_INVALID(10002, "无效的Token"),
    TOKEN_EXPIRED(10004, "token已过期");

    private int code;

    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
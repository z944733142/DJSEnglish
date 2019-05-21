package com.djsenglish.common;

public enum ResponseCode {
    /**
     * 成功
     */
    SUCCESS(0, "SUCCESS"),
    /**
     * 失败
     */
    ERROR(1, "ERROR"),
    /**
     * 需要登录
     */
    NEED_LOGIN(10, "NEED_LOGIN"),
    /**
     * 参数非法
     */
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    /**
     * 需要验证手机
     */
    NEED_PHONE(20, "ILLEGAL_ARGUMENT");

    private final int code;
    private final String des;

    ResponseCode(int code, String des)
    {
        this.code = code;
        this.des = des;
    }

    public int getCode() {
        return code;
    }

    public String getDes() {
        return des;
    }
}

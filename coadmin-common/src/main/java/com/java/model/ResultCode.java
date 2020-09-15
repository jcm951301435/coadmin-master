package com.java.model;

/**
 * 响应码
 *
 * @author: jcm
 * @date: 2020/05/28
 */
public enum ResultCode {

    /* 操作成功 */
    SUCCESS(200, "操作成功"),

    /* 操作失败 */
    FAILED(500, "操作失败"),

    /* 参数检验失败 */
    VALIDATE_FAILED(404, "参数检验失败"),

    /* 暂未登录或token已经过期 */
    UNAUTHORIZED(401, "暂未登录或登录超时"),

    /* 没有相关权限 */
    FORBIDDEN(403, "没有相关权限");

    private int code;

    private String message;

    ResultCode(int code, String message) {
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
}

package com.java.common.model;

import com.java.common.util.StringUtils;

/**
 * 基础返回信息类
 *
 * @author: jcm
 * @date: 2020/05/19
 */
public class CommonResult<T> {

    private int code;

    private String message;

    private T data;

    /**
     * 返回成功信息
     *
     * @param data .
     * @param <T>  .
     * @return .
     */
    public static <T> CommonResult<T> success(T data) {
        return CommonResult.success(data, ResultCode.SUCCESS.getMessage());
    }

    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 返回失败 500
     *
     * @param message .
     * @param <T>     .
     * @return .
     */
    public static <T> CommonResult<T> failure(String message) {
        return new CommonResult<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 返回失败 500
     *
     * @param resultCode .
     * @param message    .
     * @param <T>        .
     * @return .
     */
    public static <T> CommonResult<T> failure(ResultCode resultCode, String message) {
        return new CommonResult<>(resultCode.getCode(), message, null);
    }

    /**
     * 返回失败 400
     *
     * @param message .
     * @param <T>     .
     * @return .
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果 401
     *
     * @param data .
     * @param <T>  .
     * @return .
     */
    public static <T> CommonResult<T> unauthorized(String message, T data) {
        if (StringUtils.isEmpty(message)) {
            message = ResultCode.UNAUTHORIZED.getMessage();
        }
        return new CommonResult<>(ResultCode.UNAUTHORIZED.getCode(), message, data);
    }

    /**
     * 未授权返回结果 403
     *
     * @param data .
     * @param <T>  .
     * @return .
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
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

    public CommonResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

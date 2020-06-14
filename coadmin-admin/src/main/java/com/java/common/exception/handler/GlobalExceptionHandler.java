package com.java.common.exception.handler;

import com.java.common.exception.DuplicateEntityException;
import com.java.common.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 全局异常处理
 *
 * @author: jcm
 * @date: 2020/05/28
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 重复实体
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(value = DuplicateEntityException.class)
    public CommonResult<String> duplicateEntityException(DuplicateEntityException e) {
        log.error("数据库已存在此记录", e);
        return CommonResult.failure(e.getMessage());
    }

    /**
     * 数据库异常
     * @param e .
     * @return .
     */
    @ExceptionHandler(value = SQLException.class)
    public CommonResult<String> sqlException(SQLException e) {
        log.error("数据库异常", e);
        return CommonResult.failure("系统异常，请联系管理员");
    }

    /**
     * 认证异常
     * @param e .
     * @return .
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public CommonResult<String> badCredentialsException(BadCredentialsException e) {
        log.error("认证异常", e);
        return CommonResult.failure("用户名或密码错误");
    }

}

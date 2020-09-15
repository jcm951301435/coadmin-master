package com.java.common.exception.handler;

import com.java.exception.DuplicateEntityException;
import com.java.exception.ProjectRunTimeException;
import com.java.model.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author: jcm
 * @date: 2020/05/28
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 所有异常
     * @param e .
     * @return .
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult<String> exception(Exception e) {
        LOGGER.error("未知异常", e);
        return CommonResult.failure(e.getMessage());
    }

    /**
     * 自定义运行期异常
     * @param e .
     * @return .
     */
    @ExceptionHandler(value = ProjectRunTimeException.class)
    public CommonResult<String> projectRunTimeException(ProjectRunTimeException e) {
        LOGGER.error("运行期异常", e);
        return CommonResult.failure(e.getMessage());
    }

    /**
     * 重复实体
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(value = DuplicateEntityException.class)
    public CommonResult<String> duplicateEntityException(DuplicateEntityException e) {
        LOGGER.error("数据库已存在此记录", e);
        return CommonResult.failure(e.getMessage());
    }

    /**
     * 数据库异常
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(value = SQLException.class)
    public CommonResult<String> sqlException(SQLException e) {
        LOGGER.error("数据库异常", e);
        return CommonResult.failure("系统异常，请联系管理员");
    }

    /**
     * 认证异常
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(value = BadCredentialsException.class)
    public CommonResult<String> badCredentialsException(BadCredentialsException e) {
        LOGGER.error("认证异常", e);
        return CommonResult.failure("用户名或密码错误");
    }

    /**
     * get 参数校验异常
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public CommonResult<String> bindException(BindException e) {
        LOGGER.error("参数验证错误", e);
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        return CommonResult.failure(message);
    }

    /**
     * RequestParam 参数校验异常
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public CommonResult<String> constraintViolationException(ConstraintViolationException e) {
        LOGGER.error("参数验证错误", e);
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        return CommonResult.failure(message);
    }

    /**
     * RequestBody 参数校验异常
     *
     * @param e .
     * @return .
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        LOGGER.error("参数验证错误", e);
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return CommonResult.failure(message);
    }

}

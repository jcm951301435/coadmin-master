package com.java.handler;

import com.java.model.CommonResult;
import com.java.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 未登录
 *
 * @author: jcm
 * @date: 2020/05/28
 */
@Component
public class ResultAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final static Logger LOGGER = LoggerFactory.getLogger(ResultAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String message = null;
        if (authException instanceof InternalAuthenticationServiceException) {
            LOGGER.error("系统异常无法认证");
            message = "系统异常无法认证，请联系管理员!";
        }
        CommonResult<String> commonResult = CommonResult.unauthorized(message, null);
        String outResult = JsonUtils.objectToJson(commonResult);
        out.println(outResult);
        out.flush();
    }

}

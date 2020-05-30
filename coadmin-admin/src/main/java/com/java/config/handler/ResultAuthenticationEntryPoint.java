package com.java.config.handler;

import com.java.common.CommonResult;
import com.java.util.JsonUtils;
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

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        CommonResult<String> commonResult = CommonResult.unauthorized(authException.getMessage());
        String outResult = JsonUtils.objectToJson(commonResult);
        out.println(outResult);
        out.flush();
    }

}

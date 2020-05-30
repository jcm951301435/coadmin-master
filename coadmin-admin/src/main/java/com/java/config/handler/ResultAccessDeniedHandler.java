package com.java.config.handler;

import com.java.common.CommonResult;
import com.java.util.JsonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 无权限访问
 * @author: jcm
 * @date: 2020/05/28
 */
@Component
public class ResultAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        CommonResult<String> commonResult = CommonResult.forbidden(accessDeniedException.getMessage());
        String outResult = JsonUtils.objectToJson(commonResult);
        out.println(outResult);
        out.flush();
    }

}

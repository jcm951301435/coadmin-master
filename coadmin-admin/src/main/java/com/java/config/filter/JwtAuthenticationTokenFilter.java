package com.java.config.filter;

import com.java.config.provider.TokenProvider;
import com.java.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 认证过滤器
 *
 * @author: jcm
 * @date: 2020/05/20
 */
@Slf4j
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;

    public JwtAuthenticationTokenFilter(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String token = tokenProvider.getToken(request);
        if (StringUtils.isNotBlank(token)) {
            log.debug("the request has token, before authenticate");
            Authentication authentication = tokenProvider.getAuthentication(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("uri: {} 验证成功，name: {}", requestUri, authentication.getName());
        }
        filterChain.doFilter(request, response);
    }

}

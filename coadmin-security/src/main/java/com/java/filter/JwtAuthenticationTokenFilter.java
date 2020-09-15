package com.java.filter;

import com.java.provider.TokenProvider;
import com.java.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    private final TokenProvider tokenProvider;

    private final UserDetailsService userDetailsService;

    public JwtAuthenticationTokenFilter(TokenProvider tokenProvider, UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        String token = tokenProvider.getToken(request);
        if (StringUtils.isNotBlank(token)) {
            LOGGER.debug("the request has token, before authenticate");
            String username = tokenProvider.getUserNameByToken(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LOGGER.debug("uri: {} 验证成功，name: {}", requestUri, authentication.getName());
        }
        filterChain.doFilter(request, response);
    }

}

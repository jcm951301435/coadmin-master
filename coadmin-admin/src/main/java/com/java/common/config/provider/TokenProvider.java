package com.java.common.config.provider;

import com.java.common.config.JwtProperties;
import com.java.common.util.StringUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token 处理类
 *
 * @author: jcm
 * @date: 2020/05/20
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {

    private final JwtProperties jwtProperties;

    private Key key;

    /**
     * token 创建时间 key
     */
    private final String CLAIM_KEY_CREATED = "createTime";

    public TokenProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public void afterPropertiesSet() {
        String secretKey = jwtProperties.getSecret();
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成 token
     *
     * @param userDetails .
     * @return .
     */
    private String buildToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(1);
        claims.put(CLAIM_KEY_CREATED, new Date());
        String username = userDetails.getUsername();
        return Jwts.builder()
                .setSubject(username)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(getExpirationDate())
                .compact();
    }

    /**
     * 创建 token (加前缀)
     *
     * @param userDetails .
     * @return .
     */
    public String createToken(UserDetails userDetails) {
        String token = buildToken(userDetails);
        String tokenStartWith = jwtProperties.getTokenStartWith();
        return tokenStartWith + token;
    }

    /**
     * 获取过期时间
     *
     * @return .
     */
    private Date getExpirationDate() {
        Date now = new Date();
        Long expirationSeconds = jwtProperties.getExpiration();
        return new Date(now.getTime() + expirationSeconds * 1000);
    }

    /**
     * 验证 token 是否有效
     *
     * @param authToken .
     * @return .
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException e) {
            log.error("token 验证失败", e);
        }
        return false;
    }

    /**
     * 从 token 中获取用户名
     *
     * @param authToken .
     * @return .
     */
    public String getUserNameByToken(String authToken) {
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
        Claims claims = jws.getBody();
        return claims.getSubject();
    }

    /**
     * 从请求中获取 用户名
     *
     * @param request .
     * @return .
     */
    public String getUserName(HttpServletRequest request) {
        String token = getToken(request);
        return getUserNameByToken(token);
    }

    /**
     * 从请求中获取 token
     *
     * @param request .
     * @return .
     */
    public String getToken(HttpServletRequest request) {
        String tokenHeader = jwtProperties.getTokenHeader();
        String tokenStartWith = jwtProperties.getTokenStartWith();
        String authTokenHeader = request.getHeader(tokenHeader);
        if (StringUtils.isNotBlank(authTokenHeader) && authTokenHeader.startsWith(tokenStartWith)) {
            return authTokenHeader.replace(jwtProperties.getTokenStartWith(), "");
        }
        return null;
    }

}

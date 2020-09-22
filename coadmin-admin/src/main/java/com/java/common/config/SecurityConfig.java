package com.java.common.config;

import com.java.handler.ResultAccessDeniedHandler;
import com.java.handler.ResultAuthenticationEntryPoint;
import com.java.provider.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * security 配置
 *
 * @author: jcm
 * @date: 2020/05/17
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    private final ResultAccessDeniedHandler accessDeniedHandler;

    private final ResultAuthenticationEntryPoint authenticationEntryPoint;

    private final TokenProvider tokenProvider;

    private final UserDetailsService userDetailsService;

    public SecurityConfig(ResultAccessDeniedHandler accessDeniedHandler,
                          ResultAuthenticationEntryPoint authenticationEntryPoint,
                          TokenProvider tokenProvider, UserDetailsService userDetailsService) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // 禁用 CSRF
        httpSecurity.csrf().disable()
                .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/*.html", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()
                .antMatchers("/csrf").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/auth/login").permitAll()
                .anyRequest().authenticated();
        // 配置拦截器顺序
        /*httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);*/
        // 禁用session
        /*httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/
        // 防止iframe 造成跨域
        httpSecurity.headers()
                .frameOptions()
                .disable();
        // 认证、权限失败处理
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
    }

    /**
     * 提供 AuthenticationManager Bean
     * @return .
     * @throws Exception .
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /*@Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter(tokenProvider, userDetailsService);
    }*/

}

package com.simple4code.simple4j.configurer.security;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.simple4code.simple4j.configurer.security.filter.CustomAuthenticationFilter;
import com.simple4code.simple4j.configurer.security.filter.CustomizeAbstractSecurityInterceptor;
import com.simple4code.simple4j.configurer.security.filter.JwtAuthenticationTokenFilter;
import com.simple4code.simple4j.core.entity.Result;
import com.simple4code.simple4j.core.entity.ResultCode;
import com.simple4code.simple4j.core.utils.AccessAddressUtil;
import com.simple4code.simple4j.core.utils.JwtUtils;
import com.simple4code.simple4j.core.utils.RedisUtil;
import com.simple4code.simple4j.demo.system.entity.dto.UserDetailsDTO;
import com.simple4code.simple4j.demo.system.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @author Chen
 * @created 2020-11-06-20:20.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    ///**
    // * 登录成功处理逻辑
    // */
    //@Autowired
    //CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
    //
    ///**
    // * 登录失败处理逻辑
    // */
    //@Autowired
    //CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    //
    ///**
    // * 权限拒绝处理逻辑
    // */
    //@Autowired
    //CustomizeAccessDeniedHandler accessDeniedHandler;
    //
    ///**
    // * 匿名用户访问无权限资源时的异常
    // */
    //@Autowired
    //CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    //
    ///**
    // * 会话失效(账号被挤下线)处理逻辑
    // */
    //@Autowired
    //CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    //
    ///**
    // * 登出成功处理逻辑
    // */
    //@Autowired
    //CustomizeLogoutSuccessHandler logoutSuccessHandler;
    //
    /**
     * 访问决策管理器
     */
    @Autowired
    CustomizeAccessDecisionManager accessDecisionManager;

    /**
     * 实现权限拦截
     */
    @Autowired
    CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    private CustomizeAbstractSecurityInterceptor securityInterceptor;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder(12);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //配置认证方式等
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http相关的配置，包括登入登出、异常处理、会话管理等
        //异常处理(权限拒绝、登录失效等)
        http.cors().and().csrf().disable();
        //匿名用户访问无权限资源时的异常处理
        http.authorizeRequests()
                //antMatchers("/**").fullyAuthenticated().
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        //决策管理器
                        o.setAccessDecisionManager(accessDecisionManager);
                        //安全元数据源
                        o.setSecurityMetadataSource(securityMetadataSource);
                        return o;
                    }
                })

                //
                //.and()
                ////定义哪些URL需要被保护、哪些不需要被保护
                //.authorizeRequests()

                //.anyRequest()//任何请求,登录后可以访问
                //.access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证

                //登出
                .and()
                .logout()
                //允许所有用户
                .permitAll()
                //登出成功处理逻辑
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                        String authHeader = httpServletRequest.getHeader("Authorization");
                        if (StrUtil.startWith(authHeader, "Bearer ")) {
                            final String authToken = authHeader.substring("Bearer ".length());
                            //将token放入黑名单中
                            //redisUtil.hset("blacklist", authToken, DateUtil.now());
                            //log.info("token：{}已加入redis黑名单", authToken);
                            Map<String, Object> claims = jwtUtils.getClaims(authToken);
                            redisUtil.del(authToken);
                            log.info("token：【{}】 已登出", claims.get("id"));
                        }
                        Result result = Result.SUCCESS();
                        writeJson(httpServletResponse, result);
                    }
                })
                //登出之后删除cookie
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .and()
                //登入
                .addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                //允许所有用户
                .permitAll()
                //异常处理(权限拒绝、登录失效等)
                .and()
                .exceptionHandling()
                //权限拒绝处理逻辑
                .accessDeniedHandler(new AccessDeniedHandler() {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
                        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        Result result = new Result(ResultCode.NO_PERMISSION);
                        writeJson(httpServletResponse, result);
                    }
                })
                //匿名用户访问无权限资源时的异常处理
                .authenticationEntryPoint(new AuthenticationEntryPoint() {
                    @Override
                    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        Result result = new Result(ResultCode.UNAUTHENTICATED);
                        writeJson(httpServletResponse, result);
                    }
                })
                //会话管理
                .and()
                .sessionManagement()
                // 使用 JWT，关闭token
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //同一账号同时登录最大用户数
                .maximumSessions(1)
                //会话失效(账号被挤下线)处理逻辑
                .expiredSessionStrategy(new SessionInformationExpiredStrategy() {
                    @Override
                    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
                        Result result = new Result(ResultCode.USER_ACCOUNT_USE_BY_OTHERS);
                        HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
                        writeJson(httpServletResponse, result);
                    }
                });
        // 记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService()).tokenValiditySeconds(1000);

        // JWT Filter
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/api-docs",
                "/api-docs-ext",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/configuration/**",
                "/webjars/**",
                "/public",
                "/swagger-ui/**",
                "/doc.html", "/favicon.ico");
    }

    private void writeJson(HttpServletResponse httpServletResponse, Result result) throws IOException {
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(result));
    }

    /**
     * 跨域设置
     *
     * @return
     */
    //@Bean
    //public CorsConfigurationSource corsConfigurationSource() {
    //    CorsConfiguration corsConfiguration = new CorsConfiguration();
    //    corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
    //    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST"));
    //    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //    source.registerCorsConfiguration("/**", corsConfiguration);
    //    return source;
    //}
    @Bean
    public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        //登录成功处理逻辑
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                //更新用户表上次登录时间、更新人、更新时间等字段
                //User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                //User user = userService.getUserByUserName(userDetails.getUsername());
                //user.setLastLoginTime(new Date());
                //user.setUpdateTime(new Date());
                //user.setUpdateUser(user.getId());
                //userService.update(sysUser);

                //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
                //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展

                //获取请求的ip地址
                String ip = AccessAddressUtil.getIpAddress(httpServletRequest);
                Map<String, Object> map = Maps.newHashMap();
                map.put("ip", ip);
                //登录成功
                UserDetailsDTO userDetails = (UserDetailsDTO) authentication.getPrincipal();

                //api权限字符串
                StringBuilder sb = new StringBuilder();
                //获取到所有的可访问API权限
                userDetails.getAuthorities().forEach(perm -> {
                    sb.append(perm.getAuthority()).append(",");
                });

                //可访问的api权限字符串
                map.put("apis", sb.toString());
                map.put("companyId", userDetails.getCompanyId());
                map.put("companyName", userDetails.getCompanyName());
                map.put("id", userDetails.getId());


                String jwtToken = jwtUtils.createToken(userDetails.getUsername(), jwtUtils.expirationSeconds, map);

                //刷新时间
                Integer expire = jwtUtils.validTime * 24 * 60 * 60 * 1000;

                redisUtil.hset(jwtToken, "tokenValidTime", DateUtil.format(new Date(System.currentTimeMillis() + jwtUtils.validTime * 24 * 60 * 60 * 1000), DatePattern.NORM_DATETIME_FORMAT), expire);
                redisUtil.hset(jwtToken, "expirationTime", DateUtil.format(new Date(System.currentTimeMillis() + jwtUtils.expirationSeconds * 1000), DatePattern.NORM_DATETIME_FORMAT), expire);
                redisUtil.hset(jwtToken, "username", userDetails.getUsername(), expire);
                redisUtil.hset(jwtToken, "ip", ip, expire);
                redisUtil.hset(jwtToken, "apis", sb.toString(), expire);
                redisUtil.hset(jwtToken, "companyId", userDetails.getCompanyId(), expire);
                redisUtil.hset(jwtToken, "companyName", userDetails.getCompanyName(), expire);
                redisUtil.hset(jwtToken, "id", userDetails.getId(), expire);


                log.info("用户[{}]登录成功，信息已保存至redis", userDetails.getUsername());
                //返回json数据
                Result result = new Result(ResultCode.SUCCESS, jwtToken);
                writeJson(httpServletResponse, result);
            }
        });
        //登录失败处理逻辑
        filter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {

            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                //httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                //返回json数据
                Result result = null;
                if (e instanceof AccountExpiredException) {
                    //账号过期
                    result = new Result(ResultCode.USER_ACCOUNT_EXPIRED);
                } else if (e instanceof BadCredentialsException) {
                    //密码错误
                    result = new Result(ResultCode.USER_CREDENTIALS_ERROR);
                } else if (e instanceof CredentialsExpiredException) {
                    //密码过期
                    result = new Result(ResultCode.USER_CREDENTIALS_EXPIRED);
                } else if (e instanceof DisabledException) {
                    //账号不可用
                    result = new Result(ResultCode.USER_ACCOUNT_DISABLE);
                } else if (e instanceof LockedException) {
                    //账号锁定
                    result = new Result(ResultCode.USER_ACCOUNT_LOCKED);
                } else if (e instanceof InternalAuthenticationServiceException) {
                    //用户不存在
                    result = new Result(ResultCode.USER_ACCOUNT_NOT_EXIST);
                } else {
                    //其他错误
                    result = new Result(ResultCode.COMMON_FAIL);
                }
                //处理编码方式，防止中文乱码的情况
                writeJson(httpServletResponse, result);
            }
        });

        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }
}

package com.simple4code.simple4j.configurer.security.filter;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.simple4code.simple4j.core.entity.Result;
import com.simple4code.simple4j.core.entity.ResultCode;
import com.simple4code.simple4j.core.utils.AccessAddressUtil;
import com.simple4code.simple4j.core.utils.JwtUtils;
import com.simple4code.simple4j.core.utils.RedisUtil;
import com.simple4code.simple4j.demo.system.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * springboot+springsecurity+mybatis+JWT+Redis 实现前后端离（实战篇续）
 * https://blog.csdn.net/zzxzzxhao/article/details/83412648
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /**
     * 简化获取token数据的代码编写（判断是否登录）
     * 1.通过request获取请求token信息
     * 2.从token中解析获取claims
     * 3.将claims绑定到request域中
     */

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    UserServiceImpl userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1.通过request获取请求token信息
        String authorization = request.getHeader("Authorization");
        //获取请求的ip地址
        String currentIp = AccessAddressUtil.getIpAddress(request);

        //判断请求头信息是否为空，或者是否已Bearer开头
        if (StrUtil.startWith(authorization, "Bearer ")) {
            //获取token数据
            String token = authorization.substring("Bearer ".length());
            String username = null;
            username = jwtUtils.parseToken(token, jwtUtils.key);
            String ip = MapUtil.get(jwtUtils.getClaims(token), "ip", String.class);


            ////进入黑名单验证
            //if (redisUtil.hHasKey("blacklist", token)) {
            //    log.info("用户：{}的token：{}在黑名单之中，拒绝访问", username, token);
            //    Result result = new Result(ResultCode.USER_ACCOUNT_LOCKED);
            //    writeJson(response, result);
            //    return;
            //}

            //判断token是否过期
            /*
             * 过期的话，从redis中读取有效时间（比如七天登录有效），再refreshToken（根据以后业务加入，现在直接refresh）
             * 同时，已过期的token加入黑名单
             */
            if (redisUtil.hasKey(token)) {
                //判断redis是否有保存 过期
                String expirationTime = redisUtil.hget(token, "expirationTime").toString();
                if (!jwtUtils.validateToken(expirationTime)) {
                    String tokenValidTime = (String) redisUtil.hget(token, "tokenValidTime");
                    if (DateUtil.compare(DateUtil.parse(DateUtil.now(), DatePattern.NORM_DATE_PATTERN), DateUtil.parse(tokenValidTime, DatePattern.NORM_DATE_PATTERN)) > 0) {
                        log.info("{}token 过期", username);
                        Result result = new Result(ResultCode.USER_NOT_LOGIN);
                        writeJson(response, result);
                        return;
                    }
                }
                //if (jwtUtils.validateToken(expirationTime)) {
                //    //获得redis中用户的token刷新时效
                //    String tokenValidTime = (String) redisUtil.hget(token, "tokenValidTime");
                //    String currentTime = DateUtil.now();
                //    //这个token已作废，加入黑名单
                //    log.info("{}已作废，加入黑名单", token);
                //    redisUtil.hset("blacklist", token, DateUtil.now());
                //
                //
                //    if (DateUtil.compare(DateUtil.parse(tokenValidTime, DatePattern.NORM_DATE_PATTERN), DateUtil.parse(tokenValidTime, DatePattern.NORM_DATE_PATTERN)) > 0) {
                //
                //
                //        //超过有效期，不予刷新
                //        log.info("{}已超过有效期，不予刷新", token);
                //        Result result = new Result(ResultCode.USER_NOT_LOGIN);
                //        writeJson(response, result);
                //        return;
                //    } else {
                //        //仍在刷新时间内，则刷新token，放入请求头中
                //        String usernameByToken = (String) redisUtil.hget(token, "username");
                //        //更新username
                //        username = usernameByToken;
                //        //更新ip
                //        ip = (String) redisUtil.hget(token, "ip");
                //     String   apis = (String) redisUtil.hget(token, "apis");
                //        String  companyId = (String) redisUtil.hget(token, "companyId");
                //        String   companyName = (String) redisUtil.hget(token, "companyName");
                //        String   id = (String) redisUtil.hget(token, "id");
                //
                //        //获取请求的ip地址
                //        Map<String, Object> map = new HashMap<>();
                //        map.put("ip", ip);
                //        String jwtToken = jwtUtils.createToken(usernameByToken, jwtUtils.expirationSeconds, map);
                //
                //
                //        //更新redis 刷新时间
                //        Integer expire = jwtUtils.validTime * 24 * 60 * 60 * 1000;
                //
                //        redisUtil.hset(jwtToken, "tokenValidTime", DateUtil.format(new Date(System.currentTimeMillis() + expire), DatePattern.NORM_DATETIME_FORMAT), expire);
                //        redisUtil.hset(jwtToken, "expirationTime", DateUtil.format(new Date(System.currentTimeMillis() + jwtUtils.expirationSeconds * 1000), DatePattern.NORM_DATETIME_FORMAT), expire);
                //        redisUtil.hset(jwtToken, "username", usernameByToken, expire);
                //        redisUtil.hset(jwtToken, "ip", ip, expire);
                //
                //        redisUtil.hset(jwtToken, "apis", apis, expire);
                //        redisUtil.hset(jwtToken, "companyId", companyId, expire);
                //        redisUtil.hset(jwtToken, "companyName", companyName, expire);
                //        redisUtil.hset(jwtToken, "id", id, expire);
                //
                //
                //        //删除旧的token保存的redis
                //        redisUtil.del(token);
                //        //新的token保存到redis中
                //
                //
                //        redisUtil.hset(jwtToken, "tokenValidTime", DateUtil.format(new Date(System.currentTimeMillis() + expire), DatePattern.NORM_DATETIME_FORMAT), expire);
                //        redisUtil.hset(jwtToken, "expirationTime", DateUtil.format(new Date(System.currentTimeMillis() + jwtUtils.expirationSeconds * 1000), DatePattern.NORM_DATETIME_FORMAT), expire);
                //        redisUtil.hset(jwtToken, "username", username, expire);
                //        redisUtil.hset(jwtToken, "ip", ip, expire);
                //
                //        redisUtil.hset(jwtToken, "apis", apis, expire);
                //        redisUtil.hset(jwtToken, "companyId", companyId, expire);
                //        redisUtil.hset(jwtToken, "companyName", companyName, expire);
                //        redisUtil.hset(jwtToken, "id", id, expire);
                //
                //        log.info("redis已删除旧token：{},新token：{}已更新redis", token, jwtToken);
                //        //更新token，为了后面
                //        token = jwtToken;
                //        response.setHeader("Authorization", "Bearer " + jwtToken);
                //    }
                //}

            }

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                /*
                 * 加入对ip的验证
                 * 如果ip不正确，进入黑名单验证
                 */
                //if (!StrUtil.equals(ip, currentIp)) {
                //    //地址不正确
                //    log.info("用户：{}的ip地址变动，进入黑名单校验", username);
                //    //进入黑名单验证
                //    if (redisUtil.hHasKey("blacklist", token)) {
                //        log.info("用户：{}的token：{}在黑名单之中，拒绝访问", username, token);
                //        Result result = new Result(ResultCode.USER_ACCOUNT_LOCKED);
                //        writeJson(response, result);
                //        return;
                //    }
                //    //黑名单没有则继续，如果黑名单存在就退出后面
                //}
                //UserDetails userDetails = userService.loadUserByUsername(username);
                //if (userDetails != null) {
                //    UsernamePasswordAuthenticationToken authentication =
                //            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //
                //    SecurityContextHolder.getContext().setAuthentication(authentication);
                //}

                //Claims claims = jwtUtils.parseJwt(token);
                //if (claims != null) {
                //    //通过claims获取到当前用户的可访问API权限字符串
                //    //api-user-delete,api-user-update
                //    String apis = (String) claims.get("apis");
                //    //通过访问的url
                //    String name = request.getRequestURI();
                //    //判断当前用户是否具有响应的请求权限
                //    if (apis.contains(name)) {
                //        request.setAttribute("user_claims", claims);
                //    } else {
                //        Result result = new Result(ResultCode.UNAUTHORISE);
                //        writeJson(response, result);
                //        return;
                //    }
                //}

            }
        }

        filterChain.doFilter(request, response);

    }

    private void writeJson(HttpServletResponse httpServletResponse, Result result) throws IOException {
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSONUtil.toJsonStr(result));
    }

}
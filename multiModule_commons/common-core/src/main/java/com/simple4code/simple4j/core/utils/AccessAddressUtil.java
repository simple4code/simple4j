package com.simple4code.simple4j.core.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: zzx
 * @date: 2018/10/22 15:25
 * @description: 自定义访问地址工具类
 * 获取请求的ip地址等信息
 */
@Component
public class AccessAddressUtil {


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        boolean b = StrUtil.isBlank(ip) || StrUtil.equalsIgnoreCase("unknown", ip);
        if (b) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (b) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (b) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (b) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (b) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
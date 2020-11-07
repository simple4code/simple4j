package com.simple4code.simple4j.configurer.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.simple4code.simple4j.demo.system.entity.Permission;
import com.simple4code.simple4j.demo.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * @Author: Hutengfei
 * @Description:
 * @Date Create in 2019/9/3 21:06
 */
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private  AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Autowired
    private PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //if (StrUtil.equals(requestUrl, "/system/user/login") || StrUtil.equals(requestUrl, "/error") || StrUtil.equals(requestUrl, "/login")) {
        //    return null;
        //}
        //查询具体某个接口的权限
        List<Permission> permissionList = permissionService.selectListByPath(requestUrl);
        if (CollUtil.isEmpty(permissionList)) {
            //请求路径没有配置权限，表明该请求接口可以任意访问
            return null;
        }
        String[] attributes = new String[permissionList.size()];
        for (int i = 0; i < permissionList.size(); i++) {
            attributes[i] = permissionList.get(i).getCode();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

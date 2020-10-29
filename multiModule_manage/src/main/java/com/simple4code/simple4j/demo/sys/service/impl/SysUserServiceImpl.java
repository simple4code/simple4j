package com.simple4code.simple4j.demo.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4code.simple4j.configurer.security.jwt.JwtTokenProvider;
import com.simple4code.simple4j.core.exception.CustomException;
import com.simple4code.simple4j.demo.sys.entity.SysUser;
import com.simple4code.simple4j.demo.sys.mapper.SysUserMapper;
import com.simple4code.simple4j.demo.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-28
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, sysUserMapper.findByUsername(username));
        } catch (AuthenticationException e) {
            throw new CustomException("账号密码错误", HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }
}

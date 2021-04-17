package com.simple4j.demo.auth.service.impl;

import com.simple4j.demo.auth.entity.UserRoleDTO;
import com.simple4j.demo.sys.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Chen
 * @created 2020-10-13-13:44.
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserRoleDTO user = sysUserMapper.selectAccountRole(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }
        return User.withUsername(username)
                .password(user.getPassword())
                .authorities(String.valueOf(user.getRoles()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

        String encode = passwordEncoder.encode("simple4j");
        log.info("加密后的密码:" + encode);
        log.info("bcrypt密码对比:" + passwordEncoder.matches("simple4j", encode));
        //MD5加密前的密码为:password
        String md5Password = "{MD5}88e2d8cd1e92fd5544c8621508cd706b";
        log.info("MD5密码对比:" + passwordEncoder.matches("simple4j", encode));
    }

}
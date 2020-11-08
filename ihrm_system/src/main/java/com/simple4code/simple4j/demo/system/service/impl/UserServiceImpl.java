package com.simple4code.simple4j.demo.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.simple4code.simple4j.core.utils.IdWorker;
import com.simple4code.simple4j.demo.system.entity.Permission;
import com.simple4code.simple4j.demo.system.entity.User;
import com.simple4code.simple4j.demo.system.entity.dto.UserDetailsDTO;
import com.simple4code.simple4j.demo.system.entity.vo.UserVO;
import com.simple4code.simple4j.demo.system.mapper.UserMapper;
import com.simple4code.simple4j.demo.system.service.PermissionService;
import com.simple4code.simple4j.demo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Value("${custom.default-password}")
    private String defaultPassword;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IdWorker idWorker;

    @Override
    public UserVO findById(String userid) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.lambda().eq(User::getId, userid);
        return userMapper.getList(wrapper);
    }

    @Override
    public UserVO findByMobile(String mobile) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.lambda().eq(User::getMobile, mobile);
        return userMapper.getList(wrapper);
    }

    /**
     * 1.保存用户
     */

    @Override
    public boolean save(User user) {
        //设置主键的值
        String id = idWorker.nextId() + "";
        //String password = new Md5Hash("123456", user.getMobile(), 3).toString();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
        String password = passwordEncoder.encode(defaultPassword);
        //设置初始密码
        user.setPassword(password);
        user.setEnableState(1);
        user.setId(id);
        //调用dao保存部门
        userMapper.insert(user);

        return false;
    }

    @Override
    public Integer testBatch(Collection<User> testList) {
        return baseMapper.insertBatchSomeColumn(testList);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //需要构造出 org.springframework.security.core.userdetails.User 对象并返回
        if (StrUtil.isBlank(username)) {
            throw new RuntimeException("用户名不能为空");
        }
        //根据用户名查询用户
        //User user = userMapper.selectByName(username);
        User user = getUserByUserName(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        Set<GrantedAuthority> grantedAuthorities = Sets.newHashSet();
        if (user != null) {

            //获取该用户所拥有的权限
            List<Permission> permissions = permissionService.selectListByUser(user.getId());
            // 声明用户授权
            permissions.forEach(permission -> {
                // if (permission.getType() == PermissionConstants.PY_API) {
                //
                //}
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getCode());
                grantedAuthorities.add(grantedAuthority);
            });
        }

        log.info("用户{}验证通过", username);

        return UserDetailsDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(grantedAuthorities)
                .companyId(user.getCompanyId())
                .companyName(user.getCompanyName())
                .id(user.getId())
                .build();


    }

    @Override
    public User getUserByUserName(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        return this.getOne(userQueryWrapper);
    }

    @Override
    public void register(String username, String password) {
        log.info("用户{}注册成功", username);
    }
}

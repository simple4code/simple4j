package com.simple4code.simple4j.demo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4code.simple4j.demo.system.entity.User;
import com.simple4code.simple4j.demo.system.entity.vo.UserVO;
import com.simple4code.simple4j.demo.system.mapper.UserMapper;
import com.simple4code.simple4j.demo.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

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

    @Override
    public Integer testBatch(Collection<User> testList) {
        return baseMapper.insertBatchSomeColumn(testList);
    }
}

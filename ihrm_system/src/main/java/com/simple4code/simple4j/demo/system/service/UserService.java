package com.simple4code.simple4j.demo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.simple4code.simple4j.demo.system.entity.User;
import com.simple4code.simple4j.demo.system.entity.vo.UserVO;

import java.util.Collection;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
public interface UserService extends IService<User> {

    UserVO findById(String userid);

    UserVO findByMobile(String mobile);

    Integer testBatch(Collection<User> testList);

    User getUserByUserName(String username);

    void register(String username, String password);
}

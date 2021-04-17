package com.simple4j.demo.sys.service;

import com.simple4j.demo.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-28
 */
public interface SysUserService extends IService<SysUser> {

    String login(String username, String password);
}

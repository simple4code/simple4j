package com.simple4code.simple4j.demo.system.service.impl;

import com.simple4code.simple4j.demo.system.entity.Role;
import com.simple4code.simple4j.demo.system.mapper.RoleMapper;
import com.simple4code.simple4j.demo.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

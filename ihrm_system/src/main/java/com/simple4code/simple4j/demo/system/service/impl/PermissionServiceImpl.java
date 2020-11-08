package com.simple4code.simple4j.demo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simple4code.simple4j.demo.system.entity.Permission;
import com.simple4code.simple4j.demo.system.mapper.PermissionMapper;
import com.simple4code.simple4j.demo.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll(Map map) {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("en_visible", map.get("enVisible"));

        return permissionMapper.selectList(queryWrapper);
    }

    @Override
    public List<Permission> selectListByUser(String id) {
        return permissionMapper.selectListByUser(id);
    }

    @Override
    public List<Permission> selectListByPath(String requestUrl) {
        return permissionMapper.selectListByPath(requestUrl);
    }


}

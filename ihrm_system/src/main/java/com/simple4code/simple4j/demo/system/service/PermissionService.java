package com.simple4code.simple4j.demo.system.service;

import com.simple4code.simple4j.demo.system.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> findAll(Map map);

    List<Permission> selectListByUser(String id);

    List<Permission> selectListByPath(String requestUrl);
}

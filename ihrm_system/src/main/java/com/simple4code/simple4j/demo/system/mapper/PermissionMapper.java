package com.simple4code.simple4j.demo.system.mapper;

import com.simple4code.simple4j.demo.system.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simple4code.simple4j.demo.system.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select * from pe_permission " +
            " left join " +
            " pe_role_permission on pe_permission.id=pe_role_permission.permission_id " +
            " where  pe_role_permission.role_id =#{roleId} ")
    List<Permission> getListByRoleId(String roleId);
}

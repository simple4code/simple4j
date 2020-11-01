package com.simple4code.simple4j.demo.system.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.simple4code.simple4j.demo.system.entity.Permission;
import com.simple4code.simple4j.demo.system.entity.Role;
import com.simple4code.simple4j.demo.system.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleVO extends Role {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 说明
     */
    private String description;

    /**
     * 企业id
     */
    private String companyId;
    private Set<User> users = new HashSet<User>(0);//角色与用户   多对多

    private Set<Permission> permissions = new HashSet<Permission>(0);//角色与模块  多对多


}

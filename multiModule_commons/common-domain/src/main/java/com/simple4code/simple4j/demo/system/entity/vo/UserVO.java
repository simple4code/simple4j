package com.simple4code.simple4j.demo.system.entity.vo;

import com.simple4code.simple4j.demo.system.entity.User;
import lombok.Data;

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
public class UserVO extends User {


    /**
     * level
     *      saasAdmin:SaaS管理员。拥有所有权限
     *      coAdmin：企业管理。创建租户企业的时候添加
     *      user:企业普通用户
     */
    private String level;
    private Set<RoleVO> roles = new HashSet<RoleVO>();//用户与角色   多对多


}

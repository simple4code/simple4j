package com.simple4j.demo.sys.mapper;

import com.simple4j.demo.auth.entity.UserRoleDTO;
import com.simple4j.demo.sys.entity.SysRole;
import com.simple4j.demo.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author simple4j
 * @since 2020-10-28
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * @param
     * @param username
     * @return
     */
    @Select("select a.nickName username, a.`password` `password`,b.roleId roleId from account a,account_role b where a.nickName=#{username} and a.id=b.accountId")
    UserRoleDTO selectAccountRole(@Param("username") String username);

    @Select("select d.* from (select a.nickName username, a.`password` `password`,b.roleId roleId from account a,account_role b where a.nickName=#{username} and a.id=b.accountId ) c LEFT JOIN role d  on c.roleId = d.id")
    List<SysRole> findByUsername(@Param("username") String username);
}

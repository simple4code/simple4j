package com.simple4code.simple4j.demo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simple4code.simple4j.demo.system.entity.Role;
import com.simple4code.simple4j.demo.system.entity.vo.RoleVO;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author simple4j
 * @since 2020-10-31
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select * from pe_role " +
            " left join " +
            " pe_user_role on pe_role.id=pe_user_role.role_id " +
            " where  pe_user_role.user_id =#{userId} ")
    List<Role> getListByUserId(String userId);

    @Select("select * from pe_role " +
            " left join " +
            " pe_user_role on pe_role.id=pe_user_role.role_id " +
            " where  pe_user_role.user_id =#{userId} ")
    @Results({
            @Result(column = "id", property = "permissions",
                    many = @Many(
                            select = "com.simple4code.simple4j.demo.system.mapper.PermissionMapper.getListByRoleId"
                    )
            )
    })
    List<RoleVO> getVOListByUserId(String userId);

}

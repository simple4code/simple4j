package com.simple4code.simple4j.demo.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.simple4code.simple4j.core.config.mybatis.CustomBaseMapper;
import com.simple4code.simple4j.demo.system.entity.Permission;
import com.simple4code.simple4j.demo.system.entity.User;
import com.simple4code.simple4j.demo.system.entity.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 * https://blog.csdn.net/z357904947/article/details/97975814
 *
 * @author simple4j
 * @since 2020-10-31
 */
public interface UserMapper extends CustomBaseMapper<User> {

    @Select("select * from pe_user where ${ew.sqlSegment} ")
    @Results({
            @Result(column = "user_id", property = "id"),
            @Result(column = "id", property = "roles",
                    many = @Many(
                            select = "com.simple4code.simple4j.demo.system.mapper.RoleMapper.getVOListByUserId"
                    )
            )
    })
    UserVO getList(@Param("ew") QueryWrapper wrapper);

    /**
     * https://www.cnblogs.com/wenwuxianren/p/11032180.html
     * springboot整合mybatis-plus基于纯注解实现一对一(一对多)查询
     * <p>
     * <p>
     * https://blog.csdn.net/KingBoyWorld/article/details/78966789
     * SpringBoot使用Mybatis注解进行一对多和多对多查询
     */

    /*
    *
    *  <select id="findByMobile" resultType="com.simple4code.simple4j.demo.system.entity.dto.UserVO">

    </select>
    <select id="findById" resultType="com.simple4code.simple4j.demo.system.entity.dto.UserVO"></select>

    * */

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    User selectByName(String userName);

}

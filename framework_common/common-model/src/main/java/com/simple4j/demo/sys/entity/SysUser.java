package com.simple4j.demo.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author simple4j
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("nickName")
    private String nickName;

    @TableField("userName")
    private String userName;

    private String password;

    private String salt;

    private Integer status;

    @TableField("createAt")
    private LocalDateTime createAt;


}

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
@TableName("t_sys_user_role")
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "accountId", type = IdType.AUTO)
    private Integer accountId;

    @TableField("roleId")
    private Integer roleId;

    private Integer status;

    @TableField("createAt")
    private LocalDateTime createAt;


}

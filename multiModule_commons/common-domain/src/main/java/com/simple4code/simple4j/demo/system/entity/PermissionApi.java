package com.simple4code.simple4j.demo.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("pe_permission_api")
public class PermissionApi implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 权限等级，1为通用接口权限，2为需校验接口权限
     */
    private String apiLevel;

    /**
     * 请求类型
     */
    private String apiMethod;

    /**
     * 链接
     */
    private String apiUrl;


}

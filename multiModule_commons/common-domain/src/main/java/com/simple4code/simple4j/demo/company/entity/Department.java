package com.simple4code.simple4j.demo.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
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
@TableName("co_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 企业ID
     */
    private String companyId;

    /**
     * 父级部门ID
     */
    private String pid;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编码
     */
    private String code;

    /**
     * 部门负责人
     */
    private String manager;

    /**
     * 介绍
     */
    private String introduce;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 负责人ID
     */
    private String managerId;


}

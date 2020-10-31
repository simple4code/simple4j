package com.simple4code.simple4j.demo.company.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@TableName("co_company")
@ApiModel("企业实体类")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;


    @ApiModelProperty("公司名称")
    private String name;


    @ApiModelProperty("企业登录账号ID")
    private String managerId;

    @ApiModelProperty("当前版本")
    private String version;

    /**
     * 续期时间
     */
    private LocalDateTime renewalDate;

    @ApiModelProperty("到期时间")
    private LocalDateTime expirationDate;

    @ApiModelProperty("公司地区")
    private String companyArea;

    @ApiModelProperty("公司地址")
    private String companyAddress;

    @ApiModelProperty("营业执照-图片ID")
    private String businessLicenseId;

    /**
     * 法人代表
     */
    private String legalRepresentative;

    /**
     * 公司电话
     */
    private String companyPhone;

    /**
     * 邮箱
     */
    private String mailbox;

    /**
     * 公司规模
     */
    private String companySize;

    /**
     * 所属行业
     */
    private String industry;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 审核状态
     */
    private String auditState;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 当前余额
     */
    private Double balance;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}

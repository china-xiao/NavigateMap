package com.alibaba.web.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@ApiModel(description = "User")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("user_id")
    @ApiModelProperty("用户id")
    private String userId;
    /**
     * 邮箱,账号
     */
    @ApiModelProperty("账号")
    private String email;
    /**
     * 不能重复,可为中文
     */
    @TableField("user_name")
    @ApiModelProperty("用户名")
    private String userName;
    /**
     * shiro MD5密码32位
     */
    @ApiModelProperty(value = "用户密码")
    private String password;
    /**
     * 1启用0停用
     */
    @ApiModelProperty("1启用0停用")
    private BigDecimal state;

    @TableField("company_id")
    @ApiModelProperty("companyId")
    private String companyId;

    @TableField("company_name")
    @ApiModelProperty("companyName")
    private String companyName;

    @TableField("dept_id")
    @ApiModelProperty("deptId")
    private String deptId;

    @TableField("dept_name")
    @ApiModelProperty("deptName")
    private String deptName;
    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String gender;
    /**
     * 联系方式
     */
    @ApiModelProperty("联系方式")
    private String telephone;

    @ApiModelProperty("birthday")
    private String birthday;
    /**
     * 学历
     */
    @ApiModelProperty("账号等级")
    private Integer degree;
    /**
     * 薪资
     */
    @ApiModelProperty("薪资")
    private BigDecimal salary;
    @ApiModelProperty("remark")
    private String remark;
    /**
     * 删除标识 0-正常1-删除
     */
    @TableField("is_deleted")
    @ApiModelProperty("删除标识 0-正常1-删除")
    private Integer isDeleted;
    /**
     * 创建者id
     */
    @TableField("created_id")
    @ApiModelProperty("创建者id")
    private Long createdId;
    /**
     * 创建者名字
     */
    @TableField("created_name")
    @ApiModelProperty("创建者名字")
    private String createdName;
    /**
     * 更新者id
     */
    @TableField("updated_id")
    @ApiModelProperty("更新者id")
    private Long updatedId;
    /**
     * 更新者名字
     */
    @TableField("updated_name")
    @ApiModelProperty("更新者名字")
    private String updatedName;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date created;
    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updated;


}

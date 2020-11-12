package com.alibaba.web.entity.po;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("role_id")
    private String roleId;
    private String name;
    private String remark;
    @TableField("order_no")
    private BigDecimal orderNo;
    @TableField("company_id")
    private String companyId;
    @TableField("company_name")
    private String companyName;
    /**
     * 创建者id
     */
    @TableField("created_id")
    private Long createdId;
    /**
     * 创建者名字
     */
    @TableField("created_name")
    private String createdName;
    /**
     * 更新者id
     */
    @TableField("updated_id")
    private Long updatedId;
    /**
     * 更新者名字
     */
    @TableField("updated_name")
    private String updatedName;
    /**
     * 创建时间
     */
    private Date created;
    /**
     * 更新时间
     */
    private Date updated;
    /**
     * 删除标识 0-正常1-删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;


}

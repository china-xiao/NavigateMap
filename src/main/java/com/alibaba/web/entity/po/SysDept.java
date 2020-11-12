package com.alibaba.web.entity.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

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
@TableName("sys_dept")
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("dept_id")
    private String deptId;
    @TableField("dept_name")
    private String deptName;
    @TableField("parent_id")
    private String parentId;
    /**
     * 1代表启用，0代表停用，默认为1
     */
    private BigDecimal state;
    @TableField("company_id")
    private String companyId;
    @TableField("company_name")
    private String companyName;


}

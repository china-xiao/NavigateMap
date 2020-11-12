package com.alibaba.web.entity.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
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
@TableName("sys_module")
public class SysModule implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("module_id")
    private String moduleId;
    @TableField("parent_id")
    private String parentId;
    @TableField("parent_name")
    private String parentName;
    /**
     * 菜单名称
     */
    private String name;
    @TableField("is_leaf")
    private BigDecimal isLeaf;
    private String cpermission;
    private String curl;
    /**
     * 0 主菜单/1 左侧菜单/2按钮/3 链接/4 状态
     */
    private BigDecimal ctype;
    /**
     * 1启用0停用
     */
    private BigDecimal state;
    /**
     * 删除标识 0-正常1-删除
     */
    @TableField("is_deleted")
    private Integer isDeleted;
    /**
     * 显示顺序
     */
    @TableField("display_order")
    private Integer displayOrder;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否隐藏false.不隐藏true.隐藏
     */
    @TableField("is_hidden")
    private Integer isHidden;
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
     * 0  超管    1 如果是企业租户管理员
     */

    private String belong;


}

package com.alibaba.web.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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


    private String id;

    private String parentId;

    private String parentName;
    /**
     * 菜单名称
     */
    private String name;

    private BigDecimal isLeaf;//主菜单为空置,一级菜单为0,二级菜单为1
    private String cpermission;//权限标识
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

    private Integer isDeleted;
    /**
     * 显示顺序
     */

    private Integer displayOrder;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 是否隐藏false.不隐藏true.隐藏
     */

    private Boolean isHidden;
    /**
     * 创建者id
     */

    private String createdId;
    /**
     * 创建者名字
     */

    private String createdName;
    /**
     * 更新者id
     */

    private String updatedId;
    /**
     * 更新者名字
     */

    private String updatedName;
    /**
     * 创建时间
     */
    private LocalDateTime created;
    /**
     * 更新时间
     */
    private LocalDateTime updated;

    /**
     * 0  超管    1 如果是企业租户管理员
     */

    private String belong;


}

package com.alibaba.web.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sys_role")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;


    private String id;
    private String name;
    private String remark;

    private BigDecimal orderNo;

    private String companyId;

    private String companyName;
    /**
     * 创建者id
     */

    private Long createdId;
    /**
     * 创建者名字
     */

    private String createdName;
    /**
     * 更新者id
     */

    private Long updatedId;
    /**
     * 更新者名字
     */

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

    private Integer isDeleted;


}

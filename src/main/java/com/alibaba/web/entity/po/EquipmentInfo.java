package com.alibaba.web.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 设备信息表
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EquipmentInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private String id;

    /**
     * 设备编号
     */
    private String modelNumber;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 1启用0停用
     */
    private Integer state;

    /**
     * 设备所在横坐标
     */
    @TableField("chartX")
    private Double chartX;

    /**
     * 设备所在纵坐标
     */
    @TableField("chartY")
    private Double chartY;

    /**
     * 地址
     */
    private String address;

    /**
     * 连接设备url地址
     */
    private String url;

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


}

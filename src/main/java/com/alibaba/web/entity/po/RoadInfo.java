package com.alibaba.web.entity.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 道路信息表
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoadInfo implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    private String id;

    /**
     * 设备编号,必须唯一
     */
    private String modelNumber;

    /**
     * 天气
     */
    private String weather;

    /**
     * 温度
     */
    private String temperature;

    /**
     * 实时路况信息
     */
    private String rtti;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 实时路况信息
     */
    private String fengsu;
    private String yanwu;
    private String yudi;
    private String duche;


}

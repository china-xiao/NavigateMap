package com.alibaba.web.dao;

import com.alibaba.web.entity.po.EquipmentInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 设备信息表 Mapper 接口
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
public interface IEquipmentInfoMapper extends BaseMapper<EquipmentInfo> {

    List<EquipmentInfo> getAll();

}

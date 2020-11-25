package com.alibaba.web.service;

import com.alibaba.web.entity.po.EquipmentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 * 设备信息表 服务类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
public interface IEquipmentInfoService extends IService<EquipmentInfo> {

    PageInfo<EquipmentInfo> getAll(int page, int size);

    void toSave(EquipmentInfo equipmentInfo);

    void toUpdate(EquipmentInfo equipmentInfo);
}

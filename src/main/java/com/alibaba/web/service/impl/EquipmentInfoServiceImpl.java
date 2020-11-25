package com.alibaba.web.service.impl;

import com.alibaba.web.config.shiro.utils.ShiroCurrentUser;
import com.alibaba.web.dao.IEquipmentInfoMapper;
import com.alibaba.web.entity.po.EquipmentInfo;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.IEquipmentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 设备信息表 服务实现类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
@Service
public class EquipmentInfoServiceImpl extends ServiceImpl<IEquipmentInfoMapper, EquipmentInfo> implements IEquipmentInfoService {

    @Autowired
    private IEquipmentInfoMapper equipmentInfoMapper;

    @Override
    public PageInfo<EquipmentInfo> getAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<EquipmentInfo> list = equipmentInfoMapper.getAll();
        return new PageInfo<>(list);
    }

    @Override
    public void toSave(EquipmentInfo equipmentInfo) {
        User user = ShiroCurrentUser.currentLoginUser();
        equipmentInfo.setId(UUID.randomUUID().toString());
        equipmentInfo.setCreatedId(user.getId()).setCreatedName(user.getUserName()).setCreated(LocalDateTime.now());
        equipmentInfoMapper.insert(equipmentInfo);
    }

    @Override
    public void toUpdate(EquipmentInfo equipmentInfo) {
        User user = ShiroCurrentUser.currentLoginUser();
        equipmentInfo.setUpdatedId(user.getId()).setUpdatedName(user.getUserName()).setUpdated(LocalDateTime.now());
        equipmentInfoMapper.updateById(equipmentInfo);
    }
}

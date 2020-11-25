package com.alibaba.web.service.impl;

import com.alibaba.web.entity.po.RoadInfo;
import com.alibaba.web.dao.IRoadInfoMapper;
import com.alibaba.web.entity.vo.MapData;
import com.alibaba.web.service.IRoadInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * 道路信息表 服务实现类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
@Service
public class RoadInfoServiceImpl extends ServiceImpl<IRoadInfoMapper, RoadInfo> implements IRoadInfoService {

    @Autowired
    private IRoadInfoMapper roadInfoMapper;

    @Override
    public PageInfo<RoadInfo> getList(int page, int size, String modelNumber) {
        PageHelper.startPage(page, size);
        List<RoadInfo> list = this.list(new QueryWrapper<RoadInfo>().eq("model_number", modelNumber).orderByDesc("created"));
        return new PageInfo<>(list);
    }

    @Override
    public void toSave(RoadInfo roadInfo) {
        roadInfo.setId(UUID.randomUUID().toString()).setCreated(LocalDateTime.now());
        roadInfoMapper.insert(roadInfo);
    }

    //获取最新道路信息
    @Override
    public List<MapData> getRoadInfo() {
        return roadInfoMapper.getRoadInfo();
    }
}

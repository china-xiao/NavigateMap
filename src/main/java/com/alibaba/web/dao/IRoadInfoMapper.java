package com.alibaba.web.dao;

import com.alibaba.web.entity.po.RoadInfo;
import com.alibaba.web.entity.vo.MapData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 道路信息表 Mapper 接口
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
public interface IRoadInfoMapper extends BaseMapper<RoadInfo> {

    List<MapData> getRoadInfo();

}

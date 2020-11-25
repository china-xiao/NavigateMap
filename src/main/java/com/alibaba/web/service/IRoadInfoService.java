package com.alibaba.web.service;

import com.alibaba.web.entity.po.RoadInfo;
import com.alibaba.web.entity.vo.MapData;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 * 道路信息表 服务类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
public interface IRoadInfoService extends IService<RoadInfo> {

    PageInfo<RoadInfo> getList(int page, int size, String modelNumber);

    void toSave(RoadInfo roadInfo);

    List<MapData> getRoadInfo();

}

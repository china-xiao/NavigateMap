package com.alibaba.web.dao;

import com.alibaba.web.entity.po.SysModule;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
public interface ISysModuleMapper extends BaseMapper<SysModule> {
    //根据belong查询菜单列表
    List<SysModule> findByBelong(@Param("belong") int belong);

    List<SysModule> findByUserId(@Param("userId") String userId);
}

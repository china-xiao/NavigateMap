package com.alibaba.web.dao;

import com.alibaba.web.entity.po.SysDept;
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
public interface ISysDeptMapper extends BaseMapper<SysDept> {

    List<SysDept> findAll(@Param("companyId") String companyId);
}

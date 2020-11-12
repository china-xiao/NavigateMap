package com.alibaba.web.dao;

import com.alibaba.web.entity.po.SsCompany;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@Mapper
public interface ISsCompanyMapper extends BaseMapper<SsCompany> {

    List<SsCompany> findAll();

}

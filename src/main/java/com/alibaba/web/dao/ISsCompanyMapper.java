package com.alibaba.web.dao;

import com.alibaba.web.entity.po.SsCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */

public interface ISsCompanyMapper extends BaseMapper<SsCompany> {

    List<SsCompany> findAll();

}

package com.alibaba.web.service;

import com.alibaba.web.entity.po.SsCompany;
import com.baomidou.mybatisplus.service.IService;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
public interface ISsCompanyService extends IService<SsCompany> {

    List<SsCompany> findAll();

    void save(SsCompany company);

    SsCompany findById(String id);

    void update(SsCompany company);

    void delete(String id);

    PageInfo findAll(int page, int size);

}

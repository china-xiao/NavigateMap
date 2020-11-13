package com.alibaba.web.service.impl;

import com.alibaba.web.dao.ISsCompanyMapper;
import com.alibaba.web.entity.po.SsCompany;
import com.alibaba.web.service.ISsCompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@Service
@Slf4j
public class SsCompanyServiceImpl extends ServiceImpl<ISsCompanyMapper, SsCompany> implements ISsCompanyService {

    @Autowired
    private ISsCompanyMapper companyMapper;

    @Override
    public PageInfo<SsCompany> findAll(int page, int size) {
        PageHelper.startPage(page, size);
        List<SsCompany> list = companyMapper.findAll();
        return new PageInfo<>(list);
    }

    @Override
    public void toSave(SsCompany company) {
        company.setId(UUID.randomUUID().toString());
        companyMapper.insert(company);
    }

    @Override
    public void toUpdate(SsCompany company) {
        companyMapper.updateById(company);
    }
}

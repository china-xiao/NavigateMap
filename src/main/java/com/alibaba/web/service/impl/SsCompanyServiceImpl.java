package com.alibaba.web.service.impl;

import com.alibaba.web.dao.ISsCompanyMapper;
import com.alibaba.web.entity.po.SsCompany;
import com.alibaba.web.service.ISsCompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<SsCompany> findAll() {
        return companyMapper.findAll();
    }
}

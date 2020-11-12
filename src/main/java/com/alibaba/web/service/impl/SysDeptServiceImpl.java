package com.alibaba.web.service.impl;

import com.alibaba.web.dao.ISysDeptMapper;
import com.alibaba.web.entity.po.SysDept;
import com.alibaba.web.service.ISysDeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class SysDeptServiceImpl extends ServiceImpl<ISysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private ISysDeptMapper deptMapper;

    @Override
    public List<SysDept> findAll(String companyId) {
        return deptMapper.findAll(companyId);
    }
}

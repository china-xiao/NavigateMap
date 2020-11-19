package com.alibaba.web.service.impl;

import com.alibaba.web.dao.ISysDeptMapper;
import com.alibaba.web.entity.po.SysDept;
import com.alibaba.web.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class SysDeptServiceImpl extends ServiceImpl<ISysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private ISysDeptMapper deptMapper;

    @Override
    public List<SysDept> findAll(String companyId) {
        return deptMapper.findAll(companyId);
    }

    @Override
    public PageInfo<SysDept> list(int page, int size) {
        PageHelper.startPage(page, size);
        List<SysDept> list = deptMapper.findList();
        return new PageInfo<>(list);
    }

    @Override
    public void toSave(SysDept dept) {
        dept.setId(UUID.randomUUID().toString());
        deptMapper.insert(dept);
    }

    @Override
    public void toUpdate(SysDept dept) {
        deptMapper.updateById(dept);
    }
}

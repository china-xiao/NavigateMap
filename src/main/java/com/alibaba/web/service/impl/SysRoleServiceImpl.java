package com.alibaba.web.service.impl;

import com.alibaba.web.dao.ISysRoleMapper;
import com.alibaba.web.entity.po.SysRole;
import com.alibaba.web.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class SysRoleServiceImpl extends ServiceImpl<ISysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysRoleMapper roleMapper;

    @Override
    public List<SysRole> findAllRoles(String companyId) {
        return roleMapper.findAllRoles(companyId);
    }

    @Override
    public List<SysRole> findByUserId(String userId) {
        return roleMapper.findByUserId(userId);
    }
}

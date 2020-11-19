package com.alibaba.web.service.impl;

import com.alibaba.web.config.shiro.utils.ShiroCurrentUser;
import com.alibaba.web.dao.ISysRoleMapper;
import com.alibaba.web.entity.po.SysRole;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.ISysRoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Override
    public PageInfo<SysRole> findAll(String companyId, int page, int size) {
        PageHelper.startPage(page, size);
        List<SysRole> list = roleMapper.selectList(new QueryWrapper<SysRole>().eq("company_id", companyId));
        return new PageInfo<>(list);
    }

    @Override
    public void toSave(SysRole role) {
        User user = ShiroCurrentUser.currentLoginUser();
        role.setId(UUID.randomUUID().toString());
        roleMapper.insert(role);
    }

    @Override
    public void toUpdate(SysRole role) {
        roleMapper.updateById(role);
    }

    @Override
    public void updateRoleModule(String roleid, String moduleIds) {
        //1、根据角色id，删除中间表数据
        roleMapper.deleteByRoleId(roleid);
        //2、循环向中间表添加新的数据
        String[] ids = moduleIds.split(",");
        for (String mid : ids) {
            roleMapper.insertRoleModule(roleid,mid);
        }
    }

}

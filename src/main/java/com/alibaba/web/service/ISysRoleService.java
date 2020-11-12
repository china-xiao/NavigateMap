package com.alibaba.web.service;

import com.alibaba.web.entity.po.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
public interface ISysRoleService extends IService<SysRole> {

    List<SysRole> findAllRoles(String companyId);

    List<SysRole> findByUserId(String id);
}

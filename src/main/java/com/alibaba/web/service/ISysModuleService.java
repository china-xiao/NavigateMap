package com.alibaba.web.service;

import com.alibaba.web.entity.po.SysModule;
import com.alibaba.web.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface ISysModuleService extends IService<SysModule> {

    List<SysModule> findModules(User loginUser);

    PageInfo<SysModule> findAll(int page, int size);

    SysModule findById(String id);

    void toSave(SysModule module);

    void toUpdate(SysModule module);

    void delete(String id);

    List<SysModule> findByRoleId(String roleid);
}

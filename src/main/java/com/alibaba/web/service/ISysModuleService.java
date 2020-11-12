package com.alibaba.web.service;

import com.alibaba.web.entity.po.SysModule;
import com.alibaba.web.entity.po.User;
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
public interface ISysModuleService extends IService<SysModule> {

    List<SysModule> findModules(User loginUser);
}

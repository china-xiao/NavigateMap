package com.alibaba.web.service;

import com.alibaba.web.entity.po.SysDept;
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
public interface ISysDeptService extends IService<SysDept> {

    List<SysDept> findAll( String companyId);
}

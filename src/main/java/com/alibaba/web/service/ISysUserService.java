package com.alibaba.web.service;

import com.alibaba.web.entity.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
public interface ISysUserService extends IService<User> {

    User findByEmail(String email);

    PageInfo findAll(String companyId, int page, int size);

    void toSave(User user);

    void toUpdate(User user);

    void delete(String id);

    User findById(String id);

    void changeRole(String userid, String ids);
}

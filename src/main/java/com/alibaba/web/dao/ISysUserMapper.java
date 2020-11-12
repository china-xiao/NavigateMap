package com.alibaba.web.dao;

import com.alibaba.web.entity.po.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
public interface ISysUserMapper extends BaseMapper<User> {

    User findByEmail(@Param("email") String email);

    List<User> findAll(@Param("companyId") String companyId);

    void deleteUserRoles(@Param("userid") String userid);

    void insertUserRole(@Param("userid") String userid, @Param("roleId") String roleId);
}

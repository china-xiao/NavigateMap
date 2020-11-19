package com.alibaba.web.dao;

import com.alibaba.web.entity.po.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

public interface ISysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> findAllRoles(@Param("companyId") String companyId);

    List<SysRole> findByUserId(@Param("userId") String userId);

    void deleteByRoleId(@Param("roleid") String roleid);

    void insertRoleModule(@Param("roleid") String roleid, @Param("mid") String mid);
}

package com.alibaba.web.dao;

import com.alibaba.web.entity.po.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface ISysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> findAllRoles(@Param("companyId") String companyId);

    List<SysRole> findByUserId(@Param("userId") String userId);
}

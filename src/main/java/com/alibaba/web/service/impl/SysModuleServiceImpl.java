package com.alibaba.web.service.impl;

import com.alibaba.web.dao.ISysModuleMapper;
import com.alibaba.web.entity.po.SysModule;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.ISysModuleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
public class SysModuleServiceImpl extends ServiceImpl<ISysModuleMapper, SysModule> implements ISysModuleService {

    @Autowired
    private ISysModuleMapper moduleMapper;


    /**
     * 根据不同用户查询对应的菜单列表
     *      1、如果是系统管理员 user：degree==0
     *              查询菜单表 belong=0
     *      2、如果是企业租户管理员  user：degree==1
     *              查询菜单表 belong=1
     *      3、如果是企业普通员工
     *              执行RBAC权限模型查询
     */
    @Override
    public List<SysModule> findModules(User loginUser) {
            List<SysModule> moduleList = null;
            //1、如果是Saas系统管理员 user：degree==0
            if(loginUser.getDegree()==0){
                moduleList = moduleMapper.findByBelong(0);
                //2、如果是企业租户管理员  user：degree==1
            }else if(loginUser.getDegree()==1){
                //查询菜单表 belong=1
                moduleList =  moduleMapper.findByBelong(1);
            }else{
                //执行RBAC权限模型查询
                moduleList = moduleMapper.findByUserId(loginUser.getUserId());
            }
            return moduleList;
    }
}

package com.alibaba.web.controller;

import com.alibaba.web.entity.po.SysModule;
import com.alibaba.web.entity.po.SysRole;
import com.alibaba.web.service.ISysModuleService;
import com.alibaba.web.service.ISysRoleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@Controller
@RequestMapping("/system/role")
@Api(value = "/system/role", description = "角色控制器")
public class SysRoleController extends BaseController{
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysModuleService moduleService;

    @RequestMapping(value = "/list",name = "分页查询角色列表")
    public String list(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
        PageInfo<SysRole> info = roleService.findAll(companyId,page, size);
        request.setAttribute("page",info);
        return "system/role/role-list";
    }

    @RequestMapping(value = "/toAdd",name="跳转到新增角色页面")
    public String toAdd(){
        return "system/role/role-add";
    }

    @RequestMapping(value = "/edit",name="新增角色")
    public String edit(SysRole role){
        role.setCompanyId(companyId);
        role.setCompanyName(companyName);
        if(StringUtils.isEmpty(role.getId())){
            roleService.toSave(role);
        }else{
            roleService.toUpdate(role);
        }
        return "redirect:/system/role/list";
    }

    @RequestMapping(value = "/toUpdate",name="跳转到修改页面")
    public String toUpdate(String id){
        //1、根据角色id查询角色对象
        SysRole role = roleService.getById(id);
        request.setAttribute("role",role);
        return "system/role/role-update";
    }

    @RequestMapping(value = "/delete",name="删除角色")
    public String delete(String id){
        roleService.removeById(id);
        return "redirect:/system/role/list";
    }

    //跳转到分配相权限树页面
    @RequestMapping(value = "/roleModule",name = "跳转到分配相权限树页面")
    public String roleModule(String roleid ){
        //根据角色id查询角色对象
        SysRole role = roleService.getById(roleid);
        request.setAttribute("role",role);
        return "system/role/role-module";
    }

    /**
     * 构建树菜单:
     *     页面所需数据格式：
     *          [
     *              { id:111, pId:11, name:"随意勾选 1-1-1"},
     *              { id:111, pId:11, name:"随意勾选 1-1-1"},
     *              { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
     *            ]
     *     后台代码的数据格式
     *          List<Map>
     */
    @RequestMapping(value = "initModuleData",name="构建树菜单")
    @ResponseBody //把集合转成Json数组
    public List<Map> initModuleData(String roleid){
        //1、查询所有模块列表
        List<SysModule> list = moduleService.list();
        //2、根据角色id 查询对应的模块列表
        List<SysModule> roleModules = moduleService.findByRoleId(roleid);

        List<Map> modules = new ArrayList();
        for (SysModule module : list) {
            //创建Map
            Map map = new HashMap();
            map.put("id",module.getId());
            map.put("pId", module.getParentId());
            map.put("name", module.getName());
            if(roleModules.contains(module)){ //注意：集合中对象默认比地址，重写HashCode和equals方法
                map.put("checked","true");
            }
            //把map添加到list集合中
            modules.add(map);
        }

        return modules;
    }

    @RequestMapping(value = "/updateRoleModule",name = "给角色分配权限")
    public String updateRoleModule(String roleid,String moduleIds){
        //调用业务
        //System.out.println(roleid+"\t"+moduleIds);
        roleService.updateRoleModule(roleid,moduleIds);
        //跳转页面
        return "redirect:/system/role/list";
    }

}


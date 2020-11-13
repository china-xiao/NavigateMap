package com.alibaba.web.controller;

import com.alibaba.web.entity.po.SysModule;
import com.alibaba.web.service.ISysModuleService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@Controller
@RequestMapping("/system/module")
@Api(value = "/sysModule", description = "菜单控制器")
@Slf4j
public class SysModuleController extends BaseController{

    @Autowired
    private ISysModuleService moduleService;

    @RequestMapping(value = "/list",name = "分页查询列表")
    public String findAll(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
        PageInfo<SysModule> moduleList = moduleService.findAll(page,size);
        request.setAttribute("page",moduleList);
        return "system/module/module-list";
    }

    @RequestMapping(value = "/toAdd",name="跳转到新增菜单页面")
    public String toAdd(){
        //查询所有模块列表
        List<SysModule> moduleList = moduleService.list();
        request.setAttribute("menus",moduleList);
        return "system/module/module-add";
    }

    @RequestMapping(value = "/edit",name="新增修改菜单")
    public String edit(SysModule module){
        if(StringUtils.isEmpty(module.getId())){
            moduleService.toSave(module);
        }else{
            moduleService.toUpdate(module);
        }
        return "redirect:/system/module/list";
    }

    @RequestMapping(value = "/toUpdate",name="跳转到修改页面")
    public String toUpdate(String id){
        //1、根据角色id查询角色对象
        SysModule module = moduleService.findById(id);
        request.setAttribute("module",module);

        //查询所有模块列表
        List<SysModule> moduleList = moduleService.list();
        request.setAttribute("menus",moduleList);

        return "system/module/module-update";
    }

    @RequestMapping(value = "/delete",name="删除菜单")
    public String delete(String id){
        moduleService.delete(id);
        log.info("删除菜单id:{}",id);
        return "redirect:/system/module/list";
    }



}


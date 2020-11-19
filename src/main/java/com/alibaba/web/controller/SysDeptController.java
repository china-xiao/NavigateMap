package com.alibaba.web.controller;

import com.alibaba.web.config.shiro.utils.ShiroCurrentUser;
import com.alibaba.web.entity.po.SysDept;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.ISysDeptService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
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
@RequestMapping("/system/dept")
@Api(value = "/system/dept", description = "部门控制器")
public class SysDeptController extends BaseController{

    @Autowired
    private ISysDeptService deptService;

    @RequestMapping(value = "/list",name = "分页查询列表")
    public String findAll(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
        PageInfo<SysDept> deptList = deptService.list(page,size);
        request.setAttribute("page",deptList);
        return "system/dept/dept-list";
    }

    @RequestMapping(value = "/toAdd",name="跳转到新增部门页面")
    public String toAdd(){
        //查询部门列表
        User user = ShiroCurrentUser.currentLoginUser();
        List<SysDept> list = deptService.list();
        request.setAttribute("deptList", list);
        return "system/dept/dept-add";
    }

    @RequestMapping(value = "/edit",name="新增部门")
    public String edit(SysDept dept){
        dept.setCompanyId(companyId);
        dept.setCompanyName(companyName);
        if(StringUtils.isEmpty(dept.getId())){
            //如果没有选择父部门，则父部门id为null
            if(StringUtils.isEmpty(dept.getParentId())){
                dept.setCompanyId(null);
            }
            deptService.toSave(dept);
        }else{
            deptService.toUpdate(dept);
        }
        return "redirect:/system/dept/list";
    }

    @RequestMapping(value = "/toUpdate",name="跳转到修改页面")
    public String toUpdate(String id){
        //1、根据部门id查询部门对象
        SysDept dept = deptService.getById(id);
        request.setAttribute("dept",dept);
        //2、查询所有部门列表
        List<SysDept> list = deptService.list();
        request.setAttribute("deptList", list);
        return "system/dept/dept-update";
    }

    @RequestMapping(value = "/delete",name="删除部门")
    public String delete(String id){
        deptService.removeById(id);
        return "redirect:/system/dept/list";
    }

}


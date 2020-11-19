package com.alibaba.web.controller;

import com.alibaba.web.entity.po.SysDept;
import com.alibaba.web.entity.po.SysRole;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.ISysDeptService;
import com.alibaba.web.service.ISysRoleService;
import com.alibaba.web.service.ISysUserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/user")
@Api(value = "/system/user", description = "用户控制器")
public class SysUserController extends BaseController{

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysRoleService roleService;



    /**
     * @Auther : xiaoxh
     * @Date :  11:22 2020/11/10
     * @Description : TODO
     */
    @RequestMapping(value = "/list", name = "查询用户列表")
    @ApiOperation("查询用户列表")
    public String list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        PageInfo info = userService.findAll(companyId, page, size);
        request.setAttribute("page", info);
        return "system/user/user-list";
    }

    @RequestMapping(value = "/toAdd",name="跳转到新增用户页面")
    @ApiOperation("跳转到新增用户页面")
    public String toAdd(){
        //根据企业id，查询所有部门
        List<SysDept> deptList = deptService.findAll(companyId);
        request.setAttribute("deptList",deptList);
        return "system/user/user-add";
    }

    @RequestMapping(value = "/edit",name="新增用户")
    @ApiOperation("新增用户")
    public String edit(@ModelAttribute User user){
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        if(StringUtils.isEmpty(user.getId())){
            //保存
            userService.toSave(user);
        }else{
            //修改
            userService.toUpdate(user);
        }
        return "redirect:/system/user/list";
    }

    @RequestMapping(value = "/toUpdate",name="回显用户")
    @ApiOperation("回显用户")
    public String toUpdate(String id){
        //根据id查询用户对象
        User user = userService.findById(id);
        request.setAttribute("user", user);

        //根据企业id，查询所有部门
        List<SysDept> deptList = deptService.findAll(companyId);
        request.setAttribute("deptList",deptList);
        return "system/user/user-update";
    }

    @RequestMapping(value = "/delete",name="根据id删除用户")
    @ApiOperation("根据id删除用户")
    public String delete(String id){
        userService.delete(id);
        return "redirect:/system/user/list";
    }

    //展示角色列表
    @RequestMapping(value = "/roleList",name="展示角色列表")
    @ApiOperation("展示角色列表")
    public String roleList(String id){
        try {
            //1、根据用户id查询用户对象
            User user = userService.findById(id);
            request.setAttribute("user",user);
            //2、查询所有角色列表
            List<SysRole> roleList = roleService.findAllRoles(companyId);
            request.setAttribute("roleList",roleList);
            //3、根据用户id查询对应的角色列表
            List<SysRole> userRoles = roleService.findByUserId(id);
            //4、拼接用户的角色id
            String str = "";
            for (SysRole role : userRoles) {
                if (null != role.getId()){
                    str += role.getId() + ",";
                }else {
                    str = "1";
                }
            }
            request.setAttribute("roleStr", str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "system/user/user-role";
    }

    //给用户分配角色
    @RequestMapping(value = "changeRole",name="给用户分配角色")
    @ApiOperation("给用户分配角色")
    public String changeRole(String userid,String ids){
        //调用业务
        userService.changeRole(userid,ids);
        // 跳转页面
        return "redirect:/system/user/list";
    }


}


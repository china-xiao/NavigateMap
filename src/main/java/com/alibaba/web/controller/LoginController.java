package com.alibaba.web.controller;

import com.alibaba.web.entity.po.SysModule;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.ISysModuleService;
import com.alibaba.web.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/11 10:57
 * @Description：
 * @Version: 0.0.1
 **/
@Controller
public class LoginController extends BaseController{

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysModuleService moduleService;

    @RequestMapping("/login")
    public String login(String email,String password){

        try {
            //如果用户账号或密码为空，则跳转到登录页面
            if(StringUtils.isEmpty(email)||StringUtils.isEmpty(password)){
                return "redirect:/login.jsp";
            }
            //1、获得Subject对象
            Subject subject = SecurityUtils.getSubject();
            //2、构建用户名和密码对象 :AuthenticationToken
            UsernamePasswordToken upToken = new UsernamePasswordToken(email,password);
            //3、借助subject对象执行登录
            subject.login(upToken); //如果登录失败，则抛异常
            //4、得到用户对象
            User loginUser = (User) subject.getPrincipal();

            //把用户对象保存到session域中
            session.setAttribute("loginUser",loginUser);

            //查询菜单列表
            List<SysModule> moduleList = moduleService.findModules(loginUser);
            session.setAttribute("modules",moduleList);
            return "home/main";
        }catch (AuthenticationException e){
            request.setAttribute("error","账号或密码不正确");
            return "forward:/login.jsp";
        }
    }

    //退出
    @RequestMapping(value = "/logout",name="用户登出")
    public String logout(){
        SecurityUtils.getSubject().logout();   //登出
        return "forward:login.jsp";
    }

    @RequestMapping(value = "/home",name = "跳转首页")
    public String home(){
        return "home/home";
    }


}

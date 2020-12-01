package com.alibaba.web.controller;

import com.alibaba.web.entity.po.SysModule;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.ISysModuleService;
import com.alibaba.web.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LoginController extends BaseController{

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysModuleService moduleService;

    @RequestMapping("/login")
    public String login(String email,String password){

        try {
            if(StringUtils.isEmpty(email)||StringUtils.isEmpty(password)){
                return "redirect:/login.jsp";
            }
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken upToken = new UsernamePasswordToken(email,password);
            subject.login(upToken);
            User loginUser = (User) subject.getPrincipal();
            session.setAttribute("loginUser",loginUser);
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
        SecurityUtils.getSubject().logout();
        return "forward:login.jsp";
    }

    @RequestMapping(value = "/home",name = "跳转首页")
    public String home(){
        return "home/home";
    }

    @RequestMapping(value = "/1",name = "跳转登录页面")
    public String index(){
        log.info("首页....");
        return "index";
    }



}

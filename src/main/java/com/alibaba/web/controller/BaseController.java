package com.alibaba.web.controller;

import com.alibaba.web.config.shiro.utils.ShiroCurrentUser;
import com.alibaba.web.entity.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;


    protected String companyId = "1";
    protected String companyName = "xiaoxh";
    protected User user;

    @ModelAttribute //被些注解修饰的方法，会在其它Controller方法执行之间执行
    public void init(){
//        User user = (User) session.getAttribute("loginUser");
        User user = ShiroCurrentUser.currentLoginUser();
        if(user!=null){
            this.user = user;
            companyId = user.getCompanyId();
            companyName = user.getCompanyName();
        }

    }
}

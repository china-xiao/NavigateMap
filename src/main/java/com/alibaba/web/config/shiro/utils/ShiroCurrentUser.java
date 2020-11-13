package com.alibaba.web.config.shiro.utils;

import com.alibaba.web.entity.po.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/12 15:44
 * @Description：
 * @Version: 0.0.1
 **/
public class ShiroCurrentUser {
    private static Logger logger = LoggerFactory.getLogger(ShiroCurrentUser.class);

    /**
     * 获取当前登录的用户，若用户未登录，则返回未登录 json
     *
     * @return
     */
    public static User currentLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            Object principal = subject.getPrincipals().getPrimaryPrincipal();
            if (principal instanceof User){
                return (User)principal;
            }
        }
        return null;
    }
}

package com.alibaba.web.config.shiro.realm;

import com.alibaba.web.entity.po.SysModule;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.ISysModuleService;
import com.alibaba.web.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/9 16:33
 * @Description：
 * @Version: 0.0.1
 **/
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysModuleService moduleService;

    /**
     * jwt必须重写此方法，不然Shiro会报错
     * @param
     * @return
     */
//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof JwtToken;
//        return token instanceof ;
//    }



    //权限相关
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        List<SysModule> modules = moduleService.findModules(user);
        Set<String> set = new HashSet<>();
        for (SysModule module : modules) {
            set.add(module.getName());//向集合中添加权限名称
        }
        //保存集合到权限对象中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(set);
        return info;
    }

    /**
     * @Auther : xiaoxh
     * @Date :  16:34 2020/11/9
     * @Description : TODO 获取即将需要认证的信息   身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("-------身份认证方法--------");
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String email = upToken.getUsername();
        String userPwd = new String(upToken.getPassword());
        log.info("当前登录用户  账号:{},密码:{}",email,userPwd);
        //根据用户名从数据库获取密码
        User user = userService.findByEmail(email);
        if (user==null) {
            return null;
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        return new SimpleAuthenticationInfo(user, user.getPassword(),
                ByteSource.Util.bytes(email + "salt"), getName());//MD5两次、salt=username+salt加密
    }
}

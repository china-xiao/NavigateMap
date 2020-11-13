package com.alibaba.web.service.impl;

import com.alibaba.web.common.utils.MailUtils;
import com.alibaba.web.config.shiro.MD5.MD5Pwd;
import com.alibaba.web.dao.ISysUserMapper;
import com.alibaba.web.entity.po.User;
import com.alibaba.web.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserMapper, User> implements ISysUserService {

    @Autowired
    private ISysUserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        try {
            return userMapper.findByEmail(email);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PageInfo findAll(String companyId, int page, int size) {
        PageHelper.startPage(page, size);
        List<User> list = userMapper.findAll(companyId);
        return new PageInfo(list);
    }


    @Override
    public void toSave(User user) {
        String pwd = user.getPassword();
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(MD5Pwd.MD5Pwd(user.getEmail(),user.getPassword()));
        userMapper.insert(user);
         try {
            //发送邮件
             MailUtils.sendMsg(user.getEmail(), "遨游云平台内容邮件", "您的账号为：" + user.getEmail() + ",密码为：" + pwd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void toUpdate(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void delete(String id) {
        userMapper.deleteById(id);
    }

    @Override
    public User findById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public void changeRole(String userid, String ids) {
        userMapper.deleteUserRoles(userid);
        String[] roleIds = ids.split(",");
        for (String roleId : roleIds) {
            userMapper.insertUserRole(userid,roleId);
        }
    }
}

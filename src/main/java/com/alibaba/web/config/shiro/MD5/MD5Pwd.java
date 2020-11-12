package com.alibaba.web.config.shiro.MD5;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/10 11:37
 * @Description：
 * @Version: 0.0.1
 **/
public class MD5Pwd {
    public static String MD5Pwd(String username, String pwd) {
        // 加密算法MD5
        // salt盐 username + salt
        // 迭代次数
        String md5Pwd = null;
        try {
            md5Pwd = new SimpleHash("MD5", pwd, ByteSource.Util.bytes(username + "salt"), 2).toHex();
        }catch (Exception e){
            e.printStackTrace();
        }
        return md5Pwd;
    }

    public static void main(String[] args) {
        System.out.println("加密后String"+MD5Pwd("xiao","123"));
    }
}

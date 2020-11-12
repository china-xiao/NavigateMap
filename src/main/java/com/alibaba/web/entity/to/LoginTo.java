package com.alibaba.web.entity.to;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: xiaoxh
 * @Date: Created in 2020/11/11 10:59
 * @Description：
 * @Version: 0.0.1
 **/
@Data
public class LoginTo implements Serializable {

    private String email;

    private String password;

}

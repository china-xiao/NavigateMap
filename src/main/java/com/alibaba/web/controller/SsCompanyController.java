package com.alibaba.web.controller;

import com.alibaba.web.service.ISsCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@Controller
@RequestMapping("/ssCompany")
@Api(value = "/ssCompany", description = "公司控制器")
@Slf4j
public class SsCompanyController {

    @Autowired
    private ISsCompanyService companyService;

    @ApiOperation("/list")
    @RequestMapping("/list")
    public String getList(HttpServletRequest request){
        request.setAttribute("companyList",companyService.findAll());
        return null;
    }
    @ApiOperation("/hello")
    @RequestMapping("/hello")
    public String hello(HttpServletRequest request){
        System.out.println("hello");
        return "index";
    }
}


package com.alibaba.web.controller;

import com.alibaba.web.entity.po.SsCompany;
import com.alibaba.web.service.ISsCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@Controller
@RequestMapping("/company")
@Api(value = "/company", description = "公司控制器")
@Slf4j
public class SsCompanyController extends BaseController{

    @Autowired
    private ISsCompanyService companyService;


    @RequestMapping("/list")
    public String getList(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
        request.setAttribute("page",companyService.findAll(page,size));
        return "company/company-list";
    }

    @ApiOperation("/toAdd")
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "company/company-add";
    }

    @RequestMapping(value = "/edit",name="新增修改公司")
    @ApiOperation("/edit")
    public String edit(SsCompany company){
        if(StringUtils.isEmpty(company.getId())){
            companyService.toSave(company);
        }else{
            companyService.toUpdate(company);
        }
        return "redirect:/company/list";
    }

    @RequestMapping(value = "/toUpdate",name="跳转到修改页面")
    public String toUpdate(String id){
        //1、根据角色id查询公司对象
        SsCompany company = companyService.getById(id);
        request.setAttribute("company",company);
        return "company/company-update";
    }

    @RequestMapping(value = "/delete",name="删除公司")
    public String delete(String id){
        companyService.removeById(id);
        log.info("删除菜单id:{}",id);
        return "redirect:/company/list";
    }

}


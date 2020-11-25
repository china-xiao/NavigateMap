package com.alibaba.web.controller;

import com.alibaba.web.entity.po.RoadInfo;
import com.alibaba.web.service.IRoadInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 道路信息表 前端控制器
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
@Controller
@RequestMapping("/roadInfo")
@Api(value = "/roadInfo", description = "道路信息控制器")
@Slf4j
public class RoadInfoController extends BaseController{

    @Value("${baidu.ak}")
    private String baidu_ak;

    @Autowired
    private IRoadInfoService roadInfoService;

    @RequestMapping(value = "/list",name = "历史道路信息查询")
    public String List(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size,@RequestParam("modelNumber")String modelNumber){
        try {
            PageInfo<RoadInfo> RoadInfo = roadInfoService.getList(page,size,modelNumber);
            request.setAttribute("page",RoadInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "system/dept/dept-list";
    }

    @RequestMapping(value = "/seeMap",name = "跳转地图页面")
    public String seeMap(){
        request.setAttribute("ak",baidu_ak);
        return "map/first-map";
    }






}


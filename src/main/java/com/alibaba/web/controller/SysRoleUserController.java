package com.alibaba.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.web.entity.po.RoadInfo;
import com.alibaba.web.entity.vo.MapData;
import com.alibaba.web.service.IRoadInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/sysRoleUser")
@Api(value = "/sysRoleUser", description = "角色用户控制器")
@Slf4j
public class SysRoleUserController extends BaseController{

    @Autowired
    private IRoadInfoService roadInfoService;

    private int i = 1;

    @GetMapping(value = "/getRoadInfo",name = "获取最新道路信息")
    public String getRoadInfo(){
        String parse = null;
        try {
            List<MapData> list = roadInfoService.getRoadInfo();
            parse = JSONObject.toJSONString(list);
            log.info("parse"+parse);
        }catch (Exception e){
            e.printStackTrace();
        }
        return JSONObject.toJSONString(parse);
    }


    @PostMapping(value = "/edit",name="新增道路信息")
    public String edit(RoadInfo roadInfo){
        try {
            if(!StringUtils.isEmpty(roadInfo.getId()) && !StringUtils.isEmpty(roadInfo.getModelNumber())){
                roadInfoService.toSave(roadInfo);
                return "success";
            }
        }catch (Exception e){
            e.printStackTrace();
            return "false";
        }
        return "false";
    }

}


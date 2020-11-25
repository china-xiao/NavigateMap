package com.alibaba.web.controller;

import com.alibaba.web.entity.po.EquipmentInfo;
import com.alibaba.web.service.IEquipmentInfoService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 设备信息表 前端控制器
 * </p>
 *
 * @author xiaoxh
 * @since 2020-11-24
 */
@Controller
@RequestMapping("/equipmentInfo")
@Slf4j
public class EquipmentInfoController extends BaseController{

    @Autowired
    private IEquipmentInfoService equipmentInfoService;

    @RequestMapping(value = "/list",name = "分页查询列表")
    public String getAll(@RequestParam(defaultValue = "1")int page, @RequestParam(defaultValue = "10")int size){
        PageInfo<EquipmentInfo> info = equipmentInfoService.getAll(page,size);
        request.setAttribute("page",info);
        return "equipment/equipment-list";
    }

    @RequestMapping(value = "/toAdd",name="跳转到新增设备页面")
    public String toAdd(){
        return "equipment/equipment-add";
    }

    @RequestMapping(value = "/edit",name="新增设备")
    public String edit(EquipmentInfo equipmentInfo){
        if(StringUtils.isEmpty(equipmentInfo.getId())){
            equipmentInfoService.toSave(equipmentInfo);
        }else{
            equipmentInfoService.toUpdate(equipmentInfo);
        }
        return "redirect:/equipmentInfo/list";
    }

    @RequestMapping(value = "/toUpdate",name="跳转到设备修改页面")
    public String toUpdate(String id){
        //1、根据部门id查询部门对象
        EquipmentInfo equipmentInfo = equipmentInfoService.getById(id);
        request.setAttribute("equipmentInfo",equipmentInfo);
        return "equipment/equipment-update";
    }

    @RequestMapping(value = "/delete",name="删除部门")
    public String delete(String id){
        equipmentInfoService.removeById(id);
        return "redirect:/equipmentInfo/list";
    }

}


package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Permission> permissions = permissionService.findAll();
        mv.addObject("permissions",permissions);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/addPermission")
    public String addPermission(Permission permission){
        permissionService.addPermission(permission);
        return "redirect:findAll";
    }

    @RequestMapping("/deletePermissionById")
    public String deletePermissionById(String id){
        permissionService.deletePermissionById(id);
        return "redirect:findAll";
    }

}

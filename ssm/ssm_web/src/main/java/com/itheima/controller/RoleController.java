package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roles",roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/addRole")
    public String addRole(Role role){
        roleService.addRole(role);
        return "redirect:findAll";
    }

    @RequestMapping("/findRoleById")
    public ModelAndView findRoleById(String id){
        ModelAndView mv=new ModelAndView();
        Role role = roleService.findRoleById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }


    @RequestMapping("/deleteRoleById")
    public String deleteRoleById(String id){
        roleService.deleteRoleById(id);
        return "redirect:findAll";
    }

    @RequestMapping("/findPermissionNotInRole")
    public String findPermissionNotInRole(String roleId,Model model){
        model.addAttribute("roleId",roleId);
        List<Permission> permissions = roleService.findPermissionsNotInRole(roleId);
        model.addAttribute("permissions",permissions);
        return "role-permission-add";
    }

    @RequestMapping("/addPermissionsToRole")
    public String addPermissionsToRole(String roleId,String[] permissionIds){
        if (permissionIds!=null){
            roleService.addPermissionsToRole(roleId,permissionIds);
        }
        return "redirect:findAll";
    }

}

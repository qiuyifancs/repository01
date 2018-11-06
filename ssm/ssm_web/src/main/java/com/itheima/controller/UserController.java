package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<UserInfo> users = userService.findAll();
        mv.addObject("users",users);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/addUser")
    @PreAuthorize("authentication.principal.username=='Tom'")
    public String addUser(UserInfo userInfo){
        userService.addUser(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("/findUserById")
    public ModelAndView findUserById(String id){
        ModelAndView mv=new ModelAndView();
        UserInfo user = userService.findUserById(id);
        mv.addObject("user",user);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findRolesNotInUser")
    public ModelAndView findRolesNotInUser(String id){
        ModelAndView mv=new ModelAndView();
        mv.addObject("userId",id);
        List<Role> roles = userService.findRolesNotInUser(id);
        mv.addObject("roles",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRolesToUser")
    public String addRolesToUser(String userId,String[] roleIds){
        if (roleIds!=null){
            userService.addRolesToUser(userId,roleIds);
        }
        return "redirect:findAll";
    }
}

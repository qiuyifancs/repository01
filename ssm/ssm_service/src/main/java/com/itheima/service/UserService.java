package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    List<UserInfo> findAll();

    void addUser(UserInfo userInfo);

    UserInfo findUserById(String id);

    List<Role> findRolesNotInUser(String Id);

    void addRolesToUser(String userId,String[] roleIds);

}

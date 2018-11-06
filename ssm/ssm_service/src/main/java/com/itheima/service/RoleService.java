package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void addRole(Role role);

    Role findRoleById(String id);

    void deleteRoleById(String id);

    List<Permission> findPermissionsNotInRole(String roleId);

    void addPermissionsToRole(String roleId,String[] permissionIds);

}

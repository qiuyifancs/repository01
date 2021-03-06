package com.itheima.service;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll();

    void addPermission(Permission permission);

    void deletePermissionById(String id);

}

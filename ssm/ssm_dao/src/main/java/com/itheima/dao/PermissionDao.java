package com.itheima.dao;

import com.itheima.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findPermissionsByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void addPermission(Permission permission);

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteRole_PermissionByPermissionId(String id);

    @Delete("delete from permission where id=#{id}")
    void deletePermissionById(String id);

}

package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(id = "roleMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,
            many = @Many(select = "com.itheima.dao.PermissionDao.findPermissionsByRoleId")),
    })
    List<Role> findRolesByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void addRole(Role role);

    @Select("select * from role where id=#{id}")
    @ResultMap("roleMap")
    Role findRoleById(String id);

    @Delete("delete from users_role where roleId=#{id}")
    void deleteUsers_RoleByRoleId(String id);

    @Delete("delete from role_permission where roleId=#{id}")
    void deleteRole_PermissionByRoleId(String id);

    @Delete("delete from role where id=#{id}")
    void deleteRoleById(String id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findPermissionsNotInRole(String roleId);

    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);

}

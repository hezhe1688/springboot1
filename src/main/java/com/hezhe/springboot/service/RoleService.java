package com.hezhe.springboot.service;


import com.hezhe.springboot.model.Role;

import java.util.List;

public interface RoleService {

    public List<Role> selectRoles();

    public List<Role> selectRolePermission();

    public void insertRole(Role role);

    public void insertRolePermission(Integer rid, Integer pid);

    public void deleteRoleById(Integer[] ids);

    public void deleteRolePermissionByRid(Integer[] ids);
}

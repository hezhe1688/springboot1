package com.hezhe.springboot.service;

import com.hezhe.springboot.mapper.RoleMapper;
import com.hezhe.springboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> selectRoles() {
        return roleMapper.selectRoles();
    }

    @Override
    public List<Role> selectRolePermission() {
        return roleMapper.selectRolePermission();
    }

    @Override
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }

    @Override
    public void insertRolePermission(Integer rid, Integer pid) {
        roleMapper.insertRolePermission(rid, pid);
    }

    @Override
    public void deleteRoleById(Integer[] ids) {
        roleMapper.deleteRoleById(ids);
    }

    @Override
    public void deleteRolePermissionByRid(Integer[] ids) {
        roleMapper.deleteRolePermissionByRid(ids);
    }
}

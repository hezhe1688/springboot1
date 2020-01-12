package com.hezhe.springboot.service;

import com.hezhe.springboot.model.Permission;

import java.util.List;


public interface PermissionService {
    public List<Permission> selectAllPermission();

    void insertSelective(Permission permission);

    public void deletePermissionById(Integer[] ids);
}

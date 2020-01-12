package com.hezhe.springboot.service;

import com.hezhe.springboot.mapper.PermissionMapper;
import com.hezhe.springboot.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> selectAllPermission() {
        return permissionMapper.selectAllPermission();
    }

    @Override
    public void insertSelective(Permission permission) {
        permissionMapper.insertSelective(permission);
    }

    @Override
    public void deletePermissionById(Integer[] ids) {
        permissionMapper.deletePermissionById(ids);
    }
}

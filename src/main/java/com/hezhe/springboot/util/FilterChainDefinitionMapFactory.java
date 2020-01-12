package com.hezhe.springboot.util;

import com.hezhe.springboot.mapper.PermissionMapper;
import com.hezhe.springboot.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author 贺哲
 * @2020-01-12 14:25
 */
public class FilterChainDefinitionMapFactory {
    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 实现动态从数据获取permissionName
     *
     * @return
     */
    public LinkedHashMap<String, String> getFilterChainDefinitionMap() {
        /**
         * /resource/**=anon
         * /to_login=anon
         * /admin/userList.html=perms[userList]
         * /admin/addUser.html=perms[addUser]
         * /admin/**=authc
         * /**=anon
         */
        //获取所有的权限资源
        List<Permission> permissions = permissionMapper.selectAllPermission();
        //做一个LinkedHashMap,因为这些键值对都是必须有顺序的，所以要用这个map
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (Permission permission : permissions) {
            //判断该名字有是不是以"p:"开头的
            if (permission.getPermissionName().startsWith("p:")) {
                //拼接成这个样子/admin/userList.html=perms[userList]
                map.put(permission.getUrl(), "perms[" + permission.getPermissionName().substring(2) + "]");
            } else {
                //否则就直接添加进去
                map.put(permission.getUrl(), permission.getPermissionName());
            }
        }
        return map;
    }
}


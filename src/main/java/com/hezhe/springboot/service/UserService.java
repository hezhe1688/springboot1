package com.hezhe.springboot.service;


import com.hezhe.springboot.model.User;

import java.util.List;

public interface UserService {
    public List<User> getAllPagerUser();

    public User getUserByUsername(String username);

    public void insertSelective(User user);

    public void insertUser(User user);

    public void insertUserRole(Integer userId, String roleName);

    public void deleteUserById(Integer[] ids);

    public void deleteUserRoleByUid(Integer[] ids);

    public void updateSelective(User user);
}

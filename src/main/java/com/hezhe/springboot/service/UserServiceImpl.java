package com.hezhe.springboot.service;

import com.hezhe.springboot.mapper.UserMapper;
import com.hezhe.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllPagerUser() {
        return userMapper.getAllPagerUser();
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public void insertSelective(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public void insertUserRole(Integer userId, String roleName) {
        userMapper.insertUserRole(userId, roleName);
    }

    @Override
    public void deleteUserById(Integer[] ids) {
        userMapper.deleteUserById(ids);
    }

    @Override
    public void deleteUserRoleByUid(Integer[] ids) {
        userMapper.deleteUserRoleByUid(ids);
    }

    @Override
    public void updateSelective(User user) {
        userMapper.updateSelective(user);
    }
}

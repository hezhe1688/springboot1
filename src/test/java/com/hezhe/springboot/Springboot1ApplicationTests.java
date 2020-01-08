package com.hezhe.springboot;

import com.hezhe.springboot.model.Employee;
import com.hezhe.springboot.service.EmployeeService;
import com.hezhe.springboot.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Springboot1ApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    RedisUtil redisUtil;

    @Test
    public void myTest(){

        Employee emp = employeeService.getOneEmp(2);
        redisUtil.lLeftPush("user", emp.getId()+"");
        redisUtil.lLeftPush("user", emp.getEmployeeName());
        redisUtil.lLeftPush("user", emp.getGender());

    }
}

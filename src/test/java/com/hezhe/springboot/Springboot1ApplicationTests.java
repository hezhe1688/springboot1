package com.hezhe.springboot;

import com.hezhe.springboot.model.Employee;
import com.hezhe.springboot.service.EmployeeService;
import com.hezhe.springboot.service.ScheduledService;
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
    @Autowired
    ScheduledService scheduledService;


    @Test
    public void myTest() {
        Object emp2 = redisUtil.hGetAll("emp");
        Employee employee = new Employee();
        /**
         * Object不可以直接转integer类型，会报错
         * 解决办法先将Object转String，然后将String转Integer
         * Integer.parseInt(id.toString())
         */
        Object id = redisUtil.hGet("emp", "emp_id");
        String employeeName = (String) redisUtil.hGet("emp", "name");
        String gender = (String) redisUtil.hGet("emp", "gender");
        employee.setId(Integer.parseInt(id.toString()));
        employee.setEmployeeName(employeeName);
        employee.setGender(gender);
        System.out.println("&&&&" + employee);
        int i = employeeService.insertEmp(employee);
        System.out.println(i);
    }

    @Test
    public void test2(){

    }

}

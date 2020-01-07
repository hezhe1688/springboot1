package com.hezhe.springboot.service;

import com.hezhe.springboot.mapper.EmployeeMapper;
import com.hezhe.springboot.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 贺哲
 * @2020-01-07 11:40
 */
public interface EmployeeService {
    public List<EmployeeMapper> getAllEmp();

    public Employee getOneEmp(@Param("id") Integer id);
}

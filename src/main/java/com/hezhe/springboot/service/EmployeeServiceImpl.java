package com.hezhe.springboot.service;

import com.hezhe.springboot.mapper.EmployeeMapper;
import com.hezhe.springboot.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 贺哲
 * @2020-01-07 11:41
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeMapper> getAllEmp() {
        return employeeMapper.getAllEmp();
    }

    @Override
    public Employee getOneEmp(Integer id) {
        return employeeMapper.getOneEmp(id);
    }
}

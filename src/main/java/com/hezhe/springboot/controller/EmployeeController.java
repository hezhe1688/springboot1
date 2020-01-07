package com.hezhe.springboot.controller;

import com.hezhe.springboot.mapper.EmployeeMapper;
import com.hezhe.springboot.model.Employee;
import com.hezhe.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 贺哲
 * @2020-01-07 11:43
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ResponseBody
    @GetMapping("/getAllEmp")
    public List<EmployeeMapper> getAllEmp() {
        List<EmployeeMapper> emp = employeeService.getAllEmp();
        return emp;
    }

    @ResponseBody
    @GetMapping("/getOneEmp/{id}")
    public Employee getOneEmp(@PathVariable("id") Integer id){
        Employee oneEmp = employeeService.getOneEmp(id);
        return oneEmp;
    }
}

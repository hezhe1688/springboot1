package com.hezhe.springboot.controller;

import com.hezhe.springboot.model.Department;
import com.hezhe.springboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 贺哲
 * @2020-01-07 14:03
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @GetMapping("/getAllDep")
    public List<Department> getAllDep(){
        List<Department> allDepartment = departmentService.getAllDepartment();
        return allDepartment;
    }
}

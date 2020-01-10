package com.hezhe.springboot.mapper;

import com.hezhe.springboot.model.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 贺哲
 * @2020-01-07 11:37
 */
@Repository
public interface EmployeeMapper {

    public List<EmployeeMapper> getAllEmp();

    public Employee getOneEmp(@Param("id") Integer id);

    public int insertEmp(Employee employee);
}

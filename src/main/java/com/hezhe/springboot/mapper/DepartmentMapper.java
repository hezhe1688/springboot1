package com.hezhe.springboot.mapper;

import com.hezhe.springboot.model.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 贺哲
 * @2020-01-07 11:20
 */
@Repository
public interface DepartmentMapper {
    public List<Department> getAllDepartment();

    public Department getOneDep(@Param("id") Integer id);
}

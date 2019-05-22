package com.sm.service;

import com.sm.entity.Department;

import java.util.List;

public interface DepartmentService {
    /**
     * 根据账号查找管理员
     * @return List
     */
    List<Department> selectAll();

    /**
     * 新增院系
     * @param department
     * @return int
     */
    int addDepartment(Department department);
    void deleteDepartment(int id);
}

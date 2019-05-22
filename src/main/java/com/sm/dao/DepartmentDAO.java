package com.sm.dao;

import com.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    /**
     * 查询所有院系
     * @return List<Department>
     * @throws SQLException
     */
    List<Department> getAll()throws SQLException;

    /**
     * 新增院系
     * @param department
     * @return int
     * @throws SQLException
     */
    int insertDepartment(Department department) throws SQLException;

    /**
     * 点击删除按钮删除院系
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteDepartmentById (int id) throws SQLException;

}

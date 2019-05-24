package com.sm.dao;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;

public interface CClassDAO {
    /**
     * 按照院系id查询班级
     * @param departmentId
     * @return List<CClass>
     * @throws SQLException
     */
    List<CClass> selectByDepartmentId(int departmentId)throws SQLException;

    /**
     * 根据id删除班级
     * @param id
     * @return
     * @throws SQLException
     */
    int deleteClassById(int id) throws SQLException;

    /**
     * 新增班级
     * @param cClass
     * @return
     * @throws SQLException
     */
    int inserClass(CClass cClass)throws SQLException;
}

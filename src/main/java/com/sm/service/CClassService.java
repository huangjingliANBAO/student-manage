package com.sm.service;

import com.sm.entity.CClass;

import java.sql.SQLException;
import java.util.List;

public interface CClassService {
    /**
     * 根据院系id查找班级
     * @param departmentId
     * @return
     */
    List<CClass> selectByDepartmentId(int departmentId);
    void deleteClassById(int id);

    int addClass(CClass cClass);
    List<CClass> selectAll();
}

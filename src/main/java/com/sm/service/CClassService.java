package com.sm.service;

import com.sm.entity.CClass;

import java.util.List;

public interface CClassService {
    /**
     * 根据院系id查找班级
     * @param departmentId
     * @return
     */
    List<CClass> selectByDepartmentId(int departmentId);
}

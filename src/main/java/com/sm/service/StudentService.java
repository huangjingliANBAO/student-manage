package com.sm.service;

import com.sm.entity.Student;
import com.sm.entity.StudentVO;

import java.util.List;

public interface StudentService {
    /**
     * 查询学生信息
     * @return List
     */
    List<StudentVO> selectAll();
    List<StudentVO> selectByDepartmentId(int departmentId);
    List<StudentVO> selectByClassId(int classId);
    List<StudentVO> selectByKeywords(String keywords);
    int updateStudent(Student student);
    int deleteById(String id);
}

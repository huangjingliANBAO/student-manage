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
}

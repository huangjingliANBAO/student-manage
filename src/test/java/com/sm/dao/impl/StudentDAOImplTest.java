package com.sm.dao.impl;

import com.sm.dao.StudentDAO;
import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentDAOImplTest {
    private StudentDAO studentDAO = DAOFactory.getStudentDAOInstance();

    @Test
    public void selectAll() {
        List<StudentVO> studentList = null;
        try {
            studentList = studentDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentList.forEach(student -> System.out.println(student));
    }

    @Test
    public void selectByDepartmentId() {
        List<StudentVO> studentVOList = null;
        try {
            studentVOList = studentDAO.selectByDepartmentId(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByClassId() {
        List<StudentVO> studentList = null;
        try {
            studentList = studentDAO.selectByClassId(3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByKeywords() {
        List<StudentVO> studentList = null;
        try {
            studentList = studentDAO.selectByKeywords("美");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        studentList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void updateStudent() throws SQLException {
        Student student = new Student();
        student.setId("1802343301");
        student.setAddress("美国洛杉矶");
        student.setPhone("14444");
        int n = studentDAO.updateStudent(student);
        assertEquals(1, n);
    }

    @Test
    public void deleteById() throws SQLException {
        int n = studentDAO.deleteById("1802343315");
        assertEquals(1, n);
    }

    @Test
    public void insertStudent()throws SQLException {
        Student student = new Student();
        student.setId("1111");
        student.setClassId(1);
        student.setStudentName("戴维斯");
        student.setAvatar("https://huangjingli.oss-cn-beijing.aliyuncs.com/logo/%E8%A9%B9%E5%A7%86%E6%96%AF.jpg");
        student.setBirthday(new Date());
        student.setGender("男");
        student.setAvatar("美国");
        student.setPhone("3252351");
        int n = studentDAO.insertStudent(student);
        assertEquals(1,n);
    }

    @Test
    public void countByDepartmentId() {
        int n = 0;
        try {
            n = studentDAO.countByDepartmentId(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countByClassId() {
    }
}
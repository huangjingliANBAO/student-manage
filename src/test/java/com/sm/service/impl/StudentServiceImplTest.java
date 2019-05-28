package com.sm.service.impl;

import com.sm.entity.Student;
import com.sm.entity.StudentVO;
import com.sm.factory.ServiceFactory;
import com.sm.service.StudentService;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceImplTest {
private StudentService studentService = ServiceFactory.getStudentServiceInstance();
    @Test
    public void selectAll() {
        List<StudentVO> studentVOList = studentService.selectAll();
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByDepartmentId() {
        List<StudentVO> studentVOList = studentService.selectByDepartmentId(1);
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByClassId() {
        List<StudentVO> studentVOList = studentService.selectByClassId(2);
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }

    @Test
    public void selectByKeywords() {
        List<StudentVO> studentVOList = studentService.selectByKeywords("腊");
        studentVOList.forEach(studentVO -> System.out.println(studentVO));
    }
    @Test
    public void updateStudent() {
        Student student = new Student();
        student.setId("1802343311");
        student.setAddress("西班牙巴塞罗那");
        student.setPhone("133333");
        studentService.updateStudent(student);
    }

    @Test
    public void deleteById() {
     String id = "1802343310";
     studentService.deleteById(id);
    }

    @Test
    public void insertStudent() {
        Student student = new Student();
        student.setId("111221");
        student.setClassId(3);
        student.setStudentName("测试");
        student.setAvatar("https://huangjingli.oss-cn-beijing.aliyuncs.com/logo/%E8%A9%B9%E5%A7%86%E6%96%AF.jpg");
        student.setBirthday(new Date());
        student.setGender("男");
        student.setAvatar("美国");
        student.setPhone("3252351");
        studentService.insertStudent(student);
    }

    @Test
    public void countStudentByClassId() {
    }
}
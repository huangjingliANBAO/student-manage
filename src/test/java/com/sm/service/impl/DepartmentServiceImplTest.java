package com.sm.service.impl;

import com.sm.entity.Department;
import com.sm.factory.ServiceFactory;
import com.sm.service.DepartmentService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DepartmentServiceImplTest {

    private DepartmentService departmentService = ServiceFactory.getDepartmentServiceInstance();

    @Test
    public void selectAll() {
        List<Department> departmentList = departmentService.selectAll();
        departmentList.forEach(department -> System.out.println(department));
    }

    @Test
    public void addDepartment() {
        Department department = new Department();
        department.setDepartmentName("美术系");
        department.setLogo("https://huangjingli.oss-cn-beijing.aliyuncs.com/logo/75ddc1ab-563a-4043-a776-7e0190b27129.png");
        departmentService.addDepartment(department);
    }

    @Test
    public void deleteDepartment() {
        int id = 2;
        departmentService.deleteDepartment(id);
    }
}
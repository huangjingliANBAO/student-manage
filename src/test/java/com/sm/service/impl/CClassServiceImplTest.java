package com.sm.service.impl;

import com.sm.entity.CClass;
import com.sm.factory.ServiceFactory;
import com.sm.service.CClassService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CClassServiceImplTest {
    private CClassService cClassService = ServiceFactory.getCClassServiceInstance();
    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = cClassService.selectByDepartmentId(3);
        cClassList.forEach(cClass -> System.out.println(cClass));
    }

    @Test
    public void deleteClassById() {
     int id = 2;
     cClassService.deleteClassById(id);
    }

    @Test
    public void addClass() {
        CClass cClass = new CClass();
        cClass.setDepartmentId(7);
        cClass.setClassName("国际1821");
        cClassService.addClass(cClass);
    }
}
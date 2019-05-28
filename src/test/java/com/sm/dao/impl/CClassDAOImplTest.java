package com.sm.dao.impl;

import com.sm.dao.CClassDAO;
import com.sm.entity.CClass;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class CClassDAOImplTest {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();

    @Test
    public void selectByDepartmentId() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));

    }

    @Test
    public void deleteClassById() {
        int id = 1;
        try {
            cClassDAO.deleteClassById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void inserClass() {
        CClass cClass = new CClass();
        cClass.setDepartmentId(7);
        cClass.setClassName("国际1821");
        try {
            int n = cClassDAO.inserClass(cClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectAll() {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        cClassList.forEach(cClass -> System.out.println(cClass));
    }


    @Test
    public void countByDepartmentId()throws SQLException {
        int n = cClassDAO.countByDepartmentId(1);
        System.out.println(n);
    }
}
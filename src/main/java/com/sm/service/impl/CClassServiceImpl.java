package com.sm.service.impl;

        import com.sm.dao.CClassDAO;
        import com.sm.entity.CClass;
        import com.sm.factory.DAOFactory;
        import com.sm.service.CClassService;

        import java.sql.SQLException;
        import java.util.List;

public class CClassServiceImpl implements CClassService {
    private CClassDAO cClassDAO = DAOFactory.getCClassDAOInstance();
    @Override
    public List<CClass> selectByDepartmentId(int departmentId) {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectByDepartmentId(departmentId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cClassList;
    }

    @Override
    public void deleteClassById(int id) {
        try {
            cClassDAO.deleteClassById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addClass(CClass cClass) {
        int n = 0;
        try {
            n = cClassDAO.inserClass(cClass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    @Override
    public List<CClass> selectAll()  {
        List<CClass> cClassList = null;
        try {
            cClassList = cClassDAO.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cClassList;
    }
}

package com.sm.factory;

import com.sm.dao.AdminDAO;
import com.sm.dao.impl.AdminDAOImpl;

public class DAOFactory {
    public static AdminDAO getAdminDAOInstance(){
        return new AdminDAOImpl();
    }
}

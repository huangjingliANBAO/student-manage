package com.sm.factory;

import com.sm.service.AdminService;
import com.sm.service.impl.AdminServiceImpl;

public class ServiceFactory {
    public static AdminService getAdminServiceInstance(){
        return new AdminServiceImpl();
    }
}

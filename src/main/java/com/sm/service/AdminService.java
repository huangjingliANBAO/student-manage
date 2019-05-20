package com.sm.service;

import com.sm.utils.ResultEntity;

public interface AdminService {
    ResultEntity adminLogin(String account, String password);

}

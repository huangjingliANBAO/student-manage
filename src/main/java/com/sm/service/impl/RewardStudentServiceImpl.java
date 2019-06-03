package com.sm.service.impl;

import com.sm.dao.RewardStudentDAO;
import com.sm.entity.RewardStudent;
import com.sm.factory.DAOFactory;
import com.sm.service.RewardStudentService;

import java.sql.SQLException;
import java.util.List;

public class RewardStudentServiceImpl implements RewardStudentService {
    private RewardStudentDAO rewardStudentDAO = DAOFactory.getRewardStudentDAOInstance();
    @Override
    public List<RewardStudent> getAll() {
        List<RewardStudent> rewardStudentList = null;
        try {
            rewardStudentList = rewardStudentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewardStudentList;
    }

    @Override
    public int insertRewardStudent(RewardStudent rewardStudent) {
        int n = 0;
        try {
            n = rewardStudentDAO.insertRewardStudent(rewardStudent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}

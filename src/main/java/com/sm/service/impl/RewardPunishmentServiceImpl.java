package com.sm.service.impl;

import com.sm.dao.RewardSPunishmentDAO;
import com.sm.entity.RewardPunishment;
import com.sm.entity.RewardPunishmentVO;
import com.sm.factory.DAOFactory;
import com.sm.service.RewardPunishmentService;

import java.sql.SQLException;
import java.util.List;

public class RewardPunishmentServiceImpl implements RewardPunishmentService {
    private RewardSPunishmentDAO rewardSPunishmentDAO = DAOFactory.getRewardPunishmentDAOInstance();


    @Override
    public List<RewardPunishmentVO> getAll() {
        List<RewardPunishmentVO> rewardPunishmentList = null;
        try {
            rewardPunishmentList = rewardSPunishmentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rewardPunishmentList;
    }

    @Override
    public int insertRewardPunishment(RewardPunishment rewardPunishment) {
        int n = 0;
        try {
            n = rewardSPunishmentDAO.insertRewardPunishment(rewardPunishment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }
}

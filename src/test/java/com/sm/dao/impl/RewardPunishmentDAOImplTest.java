package com.sm.dao.impl;

import com.sm.dao.RewardSPunishmentDAO;
import com.sm.entity.RewardPunishment;
import com.sm.entity.RewardPunishmentVO;
import com.sm.factory.DAOFactory;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RewardPunishmentDAOImplTest {
    private RewardSPunishmentDAO rewardSPunishmentDAO= DAOFactory.getRewardPunishmentDAOInstance();

    @Test
    public void getAll() {
        List<RewardPunishmentVO> rewardPunishmentList = null;
        try {
            rewardPunishmentList = rewardSPunishmentDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rewardPunishmentList.forEach(rewardPunishment -> System.out.println(rewardPunishment));
    }

    @Test
    public void insertRewardPunishment() {
        RewardPunishment rewardPunishment = new RewardPunishment();
        rewardPunishment.setStudentId("1802343301");
        rewardPunishment.setDetails("学习标兵");
        rewardPunishment.setDate1(new Date());
        try {
             rewardSPunishmentDAO.insertRewardPunishment(rewardPunishment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
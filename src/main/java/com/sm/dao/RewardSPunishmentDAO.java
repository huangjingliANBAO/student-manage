package com.sm.dao;

import com.sm.entity.RewardPunishment;
import com.sm.entity.RewardPunishmentVO;

import java.sql.SQLException;
import java.util.List;

public interface RewardSPunishmentDAO {
    /**
     * 展示所有获奖学生(VO视图)
     * @return   List
     * @throws SQLException
     */
    List<RewardPunishmentVO> getAll()throws SQLException;

    /**
     * 新增获奖学生
     * @param rewardPunishment
     * @return int
     * @throws SQLException
     */
    int insertRewardPunishment(RewardPunishment rewardPunishment) throws SQLException;

}

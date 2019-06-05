package com.sm.service;

import com.sm.entity.RewardPunishment;
import com.sm.entity.RewardPunishmentVO;

import java.util.List;

public interface RewardPunishmentService {
    /**
     * 展示所有获奖学生
     * @return
     */
    List<RewardPunishmentVO> getAll();
    int insertRewardPunishment(RewardPunishment rewardPunishment);
}

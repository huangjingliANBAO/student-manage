package com.sm.service;

import com.sm.entity.RewardStudent;

import java.util.List;

public interface RewardStudentService {
    /**
     * 展示所有获奖学生
     * @return
     */
    List<RewardStudent> getAll();
    int insertRewardStudent(RewardStudent rewardStudent);
}

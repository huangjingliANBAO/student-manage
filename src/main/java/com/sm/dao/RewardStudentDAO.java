package com.sm.dao;

import com.sm.entity.RewardStudent;

import java.sql.SQLException;
import java.util.List;

public interface RewardStudentDAO {
    /**
     * 展示所有获奖学生
     * @return   List
     * @throws SQLException
     */
    List<RewardStudent> getAll()throws SQLException;

    /**
     * 新增获奖学生
     * @param rewardStudent
     * @return int
     * @throws SQLException
     */
    int insertRewardStudent(RewardStudent rewardStudent) throws SQLException;

}

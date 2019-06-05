package com.sm.dao.impl;

import com.sm.dao.RewardSPunishmentDAO;
import com.sm.entity.RewardPunishment;
import com.sm.entity.RewardPunishmentVO;
import com.sm.utils.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RewardPunishmentDAOImpl implements RewardSPunishmentDAO {
    @Override
    public List<RewardPunishmentVO> getAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT t1.*,t2.student_name,avatar,t3.class_name,t4.department_name \n" +
                "FROM t_rewards_and_punishments t1 \n" +
                "LEFT JOIN t_student t2 \n" +
                "ON t1.`student_id`=t2.`id`\n" +
                "LEFT JOIN t_class t3 \n" +
                "ON t2.`class_id`=t3.`id`\n" +
                "LEFT JOIN t_department t4\n" +
                "ON t3.`department_id`=t4.`id`";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
       List<RewardPunishmentVO> rewardPunishmentList = convert(rs);
       rs.close();
       pstmt.close();
       jdbcUtil.closeConnection();
       return rewardPunishmentList;
    }
    @Override
    public int insertRewardPunishment(RewardPunishment rewardPunishment) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_rewards_and_punishments(student_id,details,date1) VALUE (?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,rewardPunishment.getStudentId());
        pstmt.setString(2,rewardPunishment.getDetails());
        pstmt.setDate(3, new Date(rewardPunishment.getDate1().getTime()));
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }
    /**
     * 封装
     */
    private List<RewardPunishmentVO> convert(ResultSet rs)throws SQLException{
        List<RewardPunishmentVO> rewardPunishmentList = new ArrayList<>();
        while (rs.next()){
            RewardPunishmentVO rewardPunishmentVO = new RewardPunishmentVO();
            rewardPunishmentVO.setId(rs.getInt("id"));
            rewardPunishmentVO.setStudentId(rs.getString("student_id"));
            rewardPunishmentVO.setDetails(rs.getString("details"));
            rewardPunishmentVO.setDate1(rs.getDate("date1"));
            rewardPunishmentVO.setStudentName(rs.getString("student_name"));
            rewardPunishmentVO.setAvatar(rs.getString("avatar"));
            rewardPunishmentVO.setClassName(rs.getString("class_name"));
            rewardPunishmentVO.setDepartmentName(rs.getString("department_name"));

            rewardPunishmentList.add(rewardPunishmentVO);
        }
        return rewardPunishmentList;
    }
}

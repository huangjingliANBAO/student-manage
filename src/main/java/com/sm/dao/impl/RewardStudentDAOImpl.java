package com.sm.dao.impl;

import com.sm.dao.RewardStudentDAO;
import com.sm.entity.RewardStudent;
import com.sm.utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RewardStudentDAOImpl implements RewardStudentDAO {
    @Override
    public List<RewardStudent> getAll() throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "SELECT * FROM t_reward";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        List<RewardStudent> rewardStudentList = new ArrayList<>();
        while (rs.next()){
            RewardStudent rewardStudent = new RewardStudent();
            rewardStudent.setId(rs.getString("id"));
            rewardStudent.setDepartmentName(rs.getString("department_name"));
            rewardStudent.setClassId(rs.getString("class_id"));
            rewardStudent.setStudentName(rs.getString("student_name"));
            rewardStudent.setLogo(rs.getString("logo"));
            rewardStudent.setReward(rs.getString("reward"));
            rewardStudentList.add(rewardStudent);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return rewardStudentList;
    }

    @Override
    public int insertRewardStudent(RewardStudent rewardStudent) throws SQLException {
        JDBCUtil jdbcUtil = JDBCUtil.getInitJDBCUtil();
        Connection connection = jdbcUtil.getConnection();
        String sql = "INSERT INTO t_reward(id,department_name,class_id,student_name,logo,reward)VALUES(?,?,?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,rewardStudent.getId());
        pstmt.setString(2,rewardStudent.getDepartmentName());
        pstmt.setString(3,rewardStudent.getClassId());
        pstmt.setString(4,rewardStudent.getStudentName());
        pstmt.setString(5,rewardStudent.getLogo());
        pstmt.setString(6,rewardStudent.getReward());
        int n = pstmt.executeUpdate();
        pstmt.close();
        connection.close();
        return n;
    }
}

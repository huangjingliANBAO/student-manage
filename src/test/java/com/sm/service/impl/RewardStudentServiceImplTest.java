package com.sm.service.impl;

import com.sm.entity.RewardStudent;
import com.sm.factory.ServiceFactory;
import com.sm.service.RewardStudentService;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RewardStudentServiceImplTest {
    private RewardStudentService rewardStudentService = ServiceFactory.getRewardStudentServiceInstance();
    @Test
    public void getAll() {
        List<RewardStudent> rewardStudentList = rewardStudentService.getAll();
        rewardStudentList.forEach(rewardStudent -> System.out.println(rewardStudent));
    }

    @Test
    public void insertRewardStudent() {
        RewardStudent rewardStudent = new RewardStudent();
        rewardStudent.setDepartmentName("计算机与软件学院");
        rewardStudent.setClassId("云计算1811");
        rewardStudent.setId("1801233309");
        rewardStudent.setStudentName("小理");
        rewardStudent.setLogo("https://huangjingli.oss-cn-beijing.aliyuncs.com/img/%E5%B0%8F%E7%BA%A21.jpg");
        rewardStudent.setReward("学习标兵");
        rewardStudentService.insertRewardStudent(rewardStudent);
    }
}
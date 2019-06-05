package com.sm.service.impl;

import com.sm.entity.RewardPunishment;
import com.sm.entity.RewardPunishmentVO;
import com.sm.factory.ServiceFactory;
import com.sm.service.RewardPunishmentService;
import jdk.nashorn.internal.ir.GetSplitState;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class RewardPunishmentServiceImplTest {
private RewardPunishmentService rewardPunishmentService = ServiceFactory.getRewardPunishmentServiceInstance();
    @Test
    public void getAll() {
        List<RewardPunishmentVO> rewardPunishmentList = rewardPunishmentService.getAll();
        rewardPunishmentList.forEach(rewardPunishment -> System.out.println(rewardPunishment));
    }

    @Test
    public void insertRewardPunishment() {
    RewardPunishment rewardPunishment = new RewardPunishment();
    rewardPunishment.setStudentId("1802343301");
    rewardPunishment.setDetails("优秀团员");
    rewardPunishment.setDate1(new Date());
    rewardPunishmentService.insertRewardPunishment(rewardPunishment);
    }
}
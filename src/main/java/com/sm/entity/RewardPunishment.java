package com.sm.entity;

import java.util.Date;

public class RewardPunishment {
    private Integer id;
    private String studentId;
    private String details;
    private Date date1;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    @Override
    public String toString() {
        return "RewardPunishment{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", details='" + details + '\'' +
                ", date1=" + date1 +
                '}';
    }
}

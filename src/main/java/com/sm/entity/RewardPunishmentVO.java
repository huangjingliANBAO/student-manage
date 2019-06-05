package com.sm.entity;

import java.util.Date;

public class RewardPunishmentVO {
    private Integer id;
    private String studentId;
    private String details;
    private Date date1;
    private String studentName;
    private String avatar;
    private String className;
    private String departmentName;

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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "RewardPunishmentVO{" +
                "id=" + id +
                ", studentId='" + studentId + '\'' +
                ", details='" + details + '\'' +
                ", date1=" + date1 +
                ", studentName='" + studentName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", className='" + className + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}

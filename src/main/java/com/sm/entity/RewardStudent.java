package com.sm.entity;

public class RewardStudent {
    private String id;
    private String departmentName;
    private String classId;
    private String studentName;
    private String logo;
    private String reward;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "RewardStudent{" +
                "id='" + id + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", classId='" + classId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", logo='" + logo + '\'' +
                ", reward='" + reward + '\'' +
                '}';
    }
}

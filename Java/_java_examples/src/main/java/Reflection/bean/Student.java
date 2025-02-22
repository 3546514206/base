package com.gqz.reflect.bean;

import java.util.Date;

public class Student {
    private String studentID;
    private String studentName;
    private Date birthday;
    private int score;

    public String getStudentID() {
        return studentID;
    }


    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }


    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }


    public Date getBirthday() {
        return birthday;
    }


    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = score;
    }

    private void finishTask(String taskName) {
        System.out.println(studentName + "完成了" + taskName + "作业");
    }
}
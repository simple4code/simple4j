package com.simple4j.demo.export;

import java.util.Date;

/**
 * @author Chen
 * @created 2020-10-21-10:41.
 */

public class TestBean {
    private String name;
    private int age;
    private double score;
    private boolean isPass;
    private Date examDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean isPass) {
        this.isPass = isPass;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }
}

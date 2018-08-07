package com.fitzafful.gliveportal.db;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String nationality;
    private String sex;
    private String picture;
    private String boardingStatus;
    private String currentStatus;
    private String currentClass;
    private String dob;
    private String doa;
    private String school;
    private String school_id;

    public Student() {

    }


    public Student(String id, String name, String sex, String picture, String currentClass, String school, String school_id) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.picture = picture;
        this.currentClass = currentClass;
        this.dob = "";
        this.doa = "";
        this.school = school;
        this.school_id = school_id;
        this.nationality = "";

    }

    public Student(String id, String name, String nationality, String sex, String picture, String boardingStatus, String currentStatus, String currentClass, String school) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
        this.sex = sex;
        this.picture = picture;
        this.boardingStatus = boardingStatus;
        this.currentStatus = currentStatus;
        this.currentClass = currentClass;
        this.dob = "";
        this.doa = "";
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoa() {
        return doa;
    }

    public void setDoa(String doa) {
        this.doa = doa;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getBoardingStatus() {
        return boardingStatus;
    }

    public void setBoardingStatus(String boardingStatus) {
        this.boardingStatus = boardingStatus;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }
}

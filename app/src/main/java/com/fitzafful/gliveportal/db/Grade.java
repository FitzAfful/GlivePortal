package com.fitzafful.gliveportal.db;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Grade extends RealmObject {

    @PrimaryKey
    private Integer id = RealmAutoIncrement.getInstance().getNextIdFromModel(Grade.class);
    private String subject_id;
    private String subject;
    private String class_score;
    private String exam_score;
    private String total_score;
    private String grade;
    private String term;
    private String year;
    private RealmList<Score> scoreRealmList;


    public void setId(Integer id) {
        this.id = id;
    }


    public Grade() {

    }

   /* public int getNextKey()
    {
        int key;
        try {
            key = realm.where(Child_pages.class).max("id").intValue() + 1;
        } catch(ArrayIndexOutOfBoundsException ex) {
            key = 0;
        }
    } */

    public Grade(String subject_id, String subject, String class_score, String exam_score, String total_score, String grade, String term, String year) {
        this.subject_id = subject_id;
        this.subject = subject;
        this.class_score = class_score;
        this.exam_score = exam_score;
        this.total_score = total_score;
        this.grade = grade;
        this.term = term;
        this.year = year;


        this.id = RealmAutoIncrement.getInstance().getNextIdFromModel(Bill.class);
    }

    public Grade(String subject_id, String subject, String class_score, String exam_score, String total_score, String position, String grade, String remarks, String term, String year, RealmList<Score> scoreRealmList, String student_id) {
        this.subject_id = subject_id;
        this.subject = subject;
        this.class_score = class_score;
        this.exam_score = exam_score;
        this.total_score = total_score;
        this.grade = grade;
        this.term = term;
        this.year = year;
        this.scoreRealmList = scoreRealmList;


        this.id = RealmAutoIncrement.getInstance().getNextIdFromModel(Bill.class);
    }

    public Grade(RealmList<Score> scoreRealmList) {
        this.scoreRealmList = scoreRealmList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public RealmList<Score> getScoreRealmList() {
        return scoreRealmList;
    }

    public void setScoreRealmList(RealmList<Score> scoreRealmList) {
        this.scoreRealmList = scoreRealmList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClass_score() {
        return class_score;
    }

    public void setClass_score(String class_score) {
        this.class_score = class_score;
    }

    public String getExam_score() {
        return exam_score;
    }

    public void setExam_score(String exam_score) {
        this.exam_score = exam_score;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }


    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

}

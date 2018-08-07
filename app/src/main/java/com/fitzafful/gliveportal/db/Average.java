package com.fitzafful.gliveportal.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Average extends RealmObject {

    @PrimaryKey
    private Integer id = RealmAutoIncrement.getInstance().getNextIdFromModel(Average.class);
    private String numberOnRoll;
    //private String averageGrade;
    private String averageMark;
    private String classPosition;
    private String averageClassMark;
    private String highestMark;
    private String lowestMark;
    private String term;
    private String year;
    private String student_id;


    public String getNumberOnRoll() {
        return numberOnRoll;
    }

    public void setNumberOnRoll(String numberOnRoll) {
        this.numberOnRoll = numberOnRoll;
    }

    /* public String getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(String averageGrade) {
        this.averageGrade = averageGrade;
    }

*/
    public String getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(String averageMark) {
        this.averageMark = averageMark;
    }

    public String getClassPosition() {
        return classPosition;
    }

    public void setClassPosition(String classPosition) {
        this.classPosition = classPosition;
    }

    public String getAverageClassMark() {
        return averageClassMark;
    }

    public void setAverageClassMark(String averageClassMark) {
        this.averageClassMark = averageClassMark;
    }

    public String getHighestMark() {
        return highestMark;
    }

    public void setHighestMark(String highestMark) {
        this.highestMark = highestMark;
    }

    public String getLowestMark() {
        return lowestMark;
    }

    public void setLowestMark(String lowestMark) {
        this.lowestMark = lowestMark;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public Average() {

    }

   /* public int getNextKey()
    {
        int key;
        try {
            key = realm.where(Child_pages.class).max("id").intValue() + 1;
        } catch(ArrayIndexOutOfBoundsException ex) {
            key = 0;
        }
    }

    public Average(String subject_id, String subject, String class_score, String exam_score, String total_score, String position, String grade, String remarks, String term, String year, String student_id) {
        this.subject_id = subject_id;
        this.subject = subject;
        this.class_score = class_score;
        this.exam_score = exam_score;
        this.total_score = total_score;
        this.position = position;
        this.grade = grade;
        this.remarks = remarks;
        this.term = term;
        this.year = year;
        this.student_id = student_id;


        this.id = RealmAutoIncrement.getInstance().getNextIdFromModel(Bill.class);
    } */

    public Average(String numberOnRoll, String averageMark, String classPosition, String averageClassMark, String highestMark, String lowestMark, String term, String year, String student_id) {
        this.numberOnRoll = numberOnRoll;
        //this.averageGrade = averageGrade;
        this.averageMark = averageMark;
        this.classPosition = classPosition;
        this.averageClassMark = averageClassMark;
        this.highestMark = highestMark;
        this.lowestMark = lowestMark;
        this.term = term;
        this.year = year;
        this.student_id = student_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

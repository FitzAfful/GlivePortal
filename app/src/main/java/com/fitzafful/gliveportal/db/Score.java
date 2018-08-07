package com.fitzafful.gliveportal.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Score extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String score;
    private String position;
    private String remarks;

    public Score() {

    }

    public Score(String id, String name, String score, String position, String remarks) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.position = position;
        this.remarks = remarks;
    }

    public Score(String id, String name, String score, String position) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.position = position;
    }

    public Score(String id, String name, String score, String remarks, int remOrPos) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}

package com.fitzafful.gliveportal.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Course extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private int credit_hours;
    private String lecturer;
    private boolean core;
    private boolean registered;

    public Course() {

    }

    public boolean isCore() {
        return core;
    }

    public void setCore(boolean core) {
        this.core = core;
    }

    public Course(String id, String name, int credit_hours, String lecturer, boolean core, boolean registered) {
        this.id = id;
        this.name = name;
        this.core = core;
        this.credit_hours = credit_hours;
        this.lecturer = lecturer;
        this.registered = registered;
    }

    public int getCreditHours() {
        return credit_hours;
    }

    public void setCreditHours(int credit_hours) {
        this.credit_hours = credit_hours;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
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
}

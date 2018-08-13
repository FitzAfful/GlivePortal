package com.fitzafful.gliveportal.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Notification extends RealmObject {

    @PrimaryKey
    private Integer id = RealmAutoIncrement.getInstance().getNextIdFromModel(Notification.class);
    private String noticetype;
    private String description;
    private String venue;
    private String createdDate;
    private boolean read;


    public void setId(Integer id) {
        this.id = id;
    }


    public Notification() {

    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Notification(String noticetype, String description, String venue, String createdDate) {
        this.noticetype = noticetype;
        this.description = description;
        this.venue = venue;
        this.createdDate = createdDate;
        this.read = false;

        this.id = RealmAutoIncrement.getInstance().getNextIdFromModel(Bill.class);
    }

    public Integer getId() {
        return id;
    }

    public String getNoticeType() {
        return noticetype;
    }

    public void setNoticeType(String noticetype) {
        this.noticetype = noticetype;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
package com.fitzafful.gliveportal.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Book extends RealmObject {

    @PrimaryKey
    private String id;
    private String name;
    private String author;
    private boolean reserved;
    private boolean me_reserved;
    private String genre;
    private String img;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Book() {

    }

    public Book(String id, String name, String author, boolean reserved, boolean me_reserved, String genre, String img) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.reserved = reserved;
        this.me_reserved = me_reserved;
        this.genre = genre;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public boolean isMe_reserved() {
        return me_reserved;
    }

    public void setMe_reserved(boolean me_reserved) {
        this.me_reserved = me_reserved;
    }

    public String getLecturer() {
        return author;
    }

    public void setLecturer(String author) {
        this.author = author;
    }

    public boolean isRegistered() {
        return reserved;
    }

    public void setRegistered(boolean reserved) {
        this.reserved = reserved;
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

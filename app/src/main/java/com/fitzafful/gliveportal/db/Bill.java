package com.fitzafful.gliveportal.db;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Bill extends RealmObject {

    @PrimaryKey private Integer id = RealmAutoIncrement.getInstance().getNextIdFromModel(Bill.class);
    private String bill_name;
    private String amount;
    private String credit;
    private String term;
    private String year;
    private String type;
    private String createdDate;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Bill() {

    }

    public Bill(String bill_name, String amount, String type, String credit, String term, String year, String createdDate) {
        this.bill_name = bill_name;
        this.amount = amount;
        this.type = type;
        this.credit = credit;
        this.term = term;
        this.year = year;
        this.createdDate = createdDate;
    }

    public String getBill_name() {
        return bill_name;
    }

    public void setBill_name(String bill_name) {
        this.bill_name = bill_name;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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

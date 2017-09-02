package com.complus.community.models;

/**
 * Created by jatin on 2/9/17.
 */

public class Complaint {
    String category,threatLevel,title,desc,date,picEnabled,id;

    public Complaint() {
    }

    public Complaint(String category, String threatLevel, String title, String desc, String date, String picEnabled) {
        this.category = category;
        this.threatLevel = threatLevel;
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.picEnabled = picEnabled;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(String threatLevel) {
        this.threatLevel = threatLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPicEnabled() {
        return picEnabled;
    }

    public void setPicEnabled(String picEnabled) {
        this.picEnabled = picEnabled;
    }
}

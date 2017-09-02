package com.complus.community.models;

/**
 * Created by Aditya on 9/2/2017.
 */

public class RewardEvent {

    String title,desc,id, points,piclink;

    public RewardEvent() {
    }

    public RewardEvent(String title, String desc, String id, String points, String piclink) {
        this.title = title;
        this.desc = desc;
        this.id = id;
        this.points = points;
        this.piclink = piclink;
    }

    public String getPiclink() {
        return piclink;
    }

    public void setPiclink(String piclink) {
        this.piclink = piclink;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}

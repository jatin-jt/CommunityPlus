package com.complus.community.models;

/**
 * Created by Aditya on 9/2/2017.
 */

public class RewardEvent {

    String title,desc,id;
    int points;

    public RewardEvent() {
    }

    public RewardEvent(String title, String desc, String id, int points) {
        this.title = title;
        this.desc = desc;
        this.id = id;
        this.points = points;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


}

package com.complus.community.models;

/**
 * Created by jatin on 2/9/17.
 */

public class EarnEvent {
    String title,desc,id,startDate,endDate,location;
    int points;

    public EarnEvent() {
    }

    public EarnEvent(String title, String desc, String id, String startDate, String endDate, String location, int points) {
        this.title = title;
        this.desc = desc;
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getLocation(){return location;}

    public void setLocation(String location){this.location = location;}


}

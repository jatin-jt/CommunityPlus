package com.complus.community.models;

/**
 * Created by jatin on 2/9/17.
 */

public class EarnEvent {
    String desc, enddate, id, location, points, startdate, title;

    public EarnEvent() {
    }

    public EarnEvent(String desc, String enddate, String id, String location, String points, String startdate, String title) {
        this.desc = desc;
        this.enddate = enddate;
        this.id = id;
        this.location = location;
        this.points = points;
        this.startdate = startdate;
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}



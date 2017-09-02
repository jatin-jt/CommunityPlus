package com.complus.community.models;

/**
 * Created by Aditya on 9/2/2017.
 */

public class LeaderboardPerson {

    String rank;
    String name;
    String points;


    public LeaderboardPerson() {
    }

    public LeaderboardPerson(String rank, String name, String points) {
        this.rank = rank;
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}

package com.complus.community.models;

/**
 * Created by jatin on 2/9/17.
 */

public class User {
    String id;
    String name;
    String contact;
    String address;
    String pincode;
    String points;

    public User(String id, String name, String contact, String address, String pincode, String points) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.pincode = pincode;
        this.points = points;
    }

    public User() {
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}

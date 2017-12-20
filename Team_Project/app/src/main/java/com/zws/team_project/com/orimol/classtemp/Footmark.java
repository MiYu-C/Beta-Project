package com.zws.team_project.com.orimol.classtemp;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */

import java.io.Serializable;

public class Footmark implements Serializable {
    private String phoneNumber;
    private String location;
    private String date;
    private String photo;
    private int systemID;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getSystemID() {
        return systemID;
    }

    public void setSystemID(int systemID) {
        this.systemID = systemID;
    }
}

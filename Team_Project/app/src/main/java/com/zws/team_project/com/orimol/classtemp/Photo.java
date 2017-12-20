package com.zws.team_project.com.orimol.classtemp;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */

import java.io.Serializable;

public class Photo implements Serializable {
    private String phoneNumber;
    private String photos;
    private String photoDate;
    private long systemID;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhotos() {
        return photos;
    }

    public void setPhotos(String photos) {
        this.photos = photos;
    }

    public String getDate() {
        return photoDate;
    }

    public void setDate(String photoDate) {
        this.photoDate = photoDate;
    }

    public long getSystemID() {
        return systemID;
    }

    public void setSystemID(long systemID) {
        this.systemID = systemID;
    }
}


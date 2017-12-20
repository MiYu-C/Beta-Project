package com.zws.team_project.com.orimol.classtemp;

import java.io.Serializable;

/**
 * Created by Orimol on 2017/11/25.
 * By Orimol
 */

public class User implements Serializable {
    private String phoneNumber;
    private String userName;
    private String passWord;
    private String email;
    private String sex;
    private long systemID;

    public User() {
//        this.phoneNumber = null;
//        this.userName = null;
//        this.passWord = null;
//        this.email = null;
//        this.sex = null;
        this.systemID = -1;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getSystemID() {
        return systemID;
    }

    public void setSystemID(long systemID) {
        this.systemID = systemID;
    }

    @Override
    public String toString() {
        return "User{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", systemID=" + systemID +
                '}';
    }
}


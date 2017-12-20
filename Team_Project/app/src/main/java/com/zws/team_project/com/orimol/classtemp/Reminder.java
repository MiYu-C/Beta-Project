package com.zws.team_project.com.orimol.classtemp;

import java.io.Serializable;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */

public class Reminder implements Serializable {
    private String phoneNumber;
    private String reminderTitle;
    private String reminderText;
    private long systemID;
    private String reminderDate;

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReminderTitle() {
        return reminderTitle;
    }

    public void setReminderTitle(String reminderTitle) {
        this.reminderTitle = reminderTitle;
    }

    public String getReminderText() {
        return reminderText;
    }

    public void setReminderText(String reminderText) {
        this.reminderText = reminderText;
    }

    public long getSystemID() {
        return systemID;
    }

    public void setSystemID(long systemID) {
        this.systemID = systemID;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", reminderTitle='" + reminderTitle + '\'' +
                ", reminderText='" + reminderText + '\'' +
                ", systemID=" + systemID +
                ", reminderDate='" + reminderDate + '\'' +
                '}';
    }
}


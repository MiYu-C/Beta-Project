package com.zws.team_project.com.orimol.classtemp;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */


import java.io.Serializable;

public class Diary implements Serializable {
    private String phoneNumber;
    private String diaryTitle;
    private String diaryText;
    private String diaryDate;
    private String emotion;
    private boolean visibility;
    private long systemID;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public String getDiaryText() {
        return diaryText;
    }

    public void setDiaryText(String diaryText) {
        this.diaryText = diaryText;
    }

    public String getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(String diaryDate) {
        this.diaryDate = diaryDate;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public long getSystemID() {
        return systemID;
    }

    public void setSystemID(long systemID) {
        this.systemID = systemID;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", diaryTitle='" + diaryTitle + '\'' +
                ", diaryText='" + diaryText + '\'' +
                ", diaryDate='" + diaryDate + '\'' +
                ", emotion='" + emotion + '\'' +
                ", visibility=" + visibility +
                ", systemID=" + systemID +
                '}';
    }
}


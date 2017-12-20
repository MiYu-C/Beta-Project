package com.zws.team_project.com.orimol.classtemp;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */

import java.io.Serializable;

public class Planning implements Serializable {
    private String phoneNumber;
    private boolean isShortPlaning;
    /*
        isFinish = 0 - 未完成
        isFinish = 1 - 已完成
        isFinish = 2 - 已删除
     */
    private int isFinish;
    private String planningDate;
    private String planningText;
    private String SystemDate;
    private int systemID;

    public int isFinish() {
        return isFinish;
    }

    public void setFinish(int finish) {
        isFinish = finish;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isShortPlaning() {
        return isShortPlaning;
    }

    public void setShortPlaning(boolean shortPlaning) {
        isShortPlaning = shortPlaning;
    }

    public String getPlanningDate() {
        return planningDate;
    }

    public void setPlanningDate(String planningDate) {
        this.planningDate = planningDate;
    }

    public String getPlanningText() {
        return planningText;
    }

    public void setPlanningText(String planningText) {
        this.planningText = planningText;
    }

    public String getSystemDate() {
        return SystemDate;
    }

    public void setSystemDate(String systemDate) {
        SystemDate = systemDate;
    }

    public int getSystemID() {
        return systemID;
    }

    public void setSystemID(int systemID) {
        this.systemID = systemID;
    }
}


package com.zws.team_project.com.orimol.classtemp;

/**
 * Created by Orimol on 2017/11/17.
 * By Orimol
 */

import java.io.Serializable;

public class RuleSystem implements Serializable {
    private String phoneNumber;
    private String breakRule;
    private String disappearDate;
    private String rulesDate;
    private int systemID;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBreakRule() {
        return breakRule;
    }

    public void setBreakRule(String breakRule) {
        this.breakRule = breakRule;
    }

    public String getDisappearDate() {
        return disappearDate;
    }

    public void setDisappearDate(String disappearDate) {
        this.disappearDate = disappearDate;
    }

    public String getRulesDate() {
        return rulesDate;
    }

    public void setRulesDate(String rulesDate) {
        this.rulesDate = rulesDate;
    }

    public int getSystemID() {
        return systemID;
    }

    public void setSystemID(int systemID) {
        this.systemID = systemID;
    }
}

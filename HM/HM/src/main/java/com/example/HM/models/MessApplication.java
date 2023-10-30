package com.example.HM.models;

import java.util.Date;

public class MessApplication {
    private int applicationId;
    private  int userId;
    private int messId;
    private boolean is_active;
    private Date appliedDate;
    private Date closingDate;

    public MessApplication() {

    }

    public MessApplication(int applicationId, int userId, int messId, boolean is_active, Date appliedDate) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.messId = messId;
        this.is_active = is_active;
        this.appliedDate = appliedDate;
        this.closingDate = null;
    }

    public MessApplication(int applicationId, int userId, int messId, Date appliedDate) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.messId = messId;
        this.appliedDate = appliedDate;
        this.is_active=true;
        this.closingDate =  null;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMessId() {
        return messId;
    }

    public void setMessId(int messId) {
        this.messId = messId;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public Date getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(Date appliedDate) {
        this.appliedDate = appliedDate;
    }

    public Date getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(Date closingDate) {
        this.closingDate = closingDate;
    }

    @Override
    public String toString() {
        return "MessApplication{" +
                "applicationId=" + applicationId +
                ", userId=" + userId +
                ", messId=" + messId +
                ", is_active=" + is_active +
                ", appliedDate=" + appliedDate +
                ", closingDate=" + closingDate +
                '}';
    }
}

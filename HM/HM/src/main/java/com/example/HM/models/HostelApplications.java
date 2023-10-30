package com.example.HM.models;


import java.sql.Date;

public class HostelApplications {

    private int applicationId;
    private int userId;
    private int hostelId;
    private boolean is_active;
    private Date appliedDate;
    private Date closingDate;

    public HostelApplications() {
    }

    public HostelApplications(int userId, int hostelId, boolean is_active, Date appliedDate) {
        this.userId = userId;
        this.hostelId = hostelId;
        this.is_active = is_active;
        this.appliedDate = appliedDate;
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

    public int getHostelId() {
        return hostelId;
    }

    public void setHostelId(int hostelId) {
        this.hostelId = hostelId;
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
}

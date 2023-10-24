package com.example.HM.models;


public class HostelApplications {

    private int applicationId;
    private  int userId;
    private String hostelName;
    private boolean is_active;

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

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public HostelApplications(int applicationId, int userId, String hostelName, boolean is_active) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.hostelName = hostelName;
        this.is_active = is_active;
    }

    public HostelApplications() {
    }
}

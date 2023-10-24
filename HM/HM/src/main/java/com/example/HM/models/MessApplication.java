package com.example.HM.models;

public class MessApplication {
    private int applicationId;
    private  int userId;
    private String messName;
    private boolean is_active;

    public MessApplication() {

    }

    public MessApplication(int applicationId, int userId, String messName, boolean is_active) {
        this.applicationId = applicationId;
        this.userId = userId;
        this.messName = messName;
        this.is_active = is_active;
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

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}

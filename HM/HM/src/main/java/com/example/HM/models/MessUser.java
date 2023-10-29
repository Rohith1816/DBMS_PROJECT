package com.example.HM.models;

import java.sql.Date;

public class MessUser {
    private String MessName;
    private String status;
    private Date appliedDate;
    private Date closingDate;

    public MessUser(String messName, String status, Date appliedDate, Date closingDate) {
        MessName = messName;
        this.status = status;
        this.appliedDate = appliedDate;
        this.closingDate = closingDate;
    }

    public MessUser() {
    }

    public String getMessName() {
        return MessName;
    }

    public void setMessName(String messName) {
        MessName = messName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

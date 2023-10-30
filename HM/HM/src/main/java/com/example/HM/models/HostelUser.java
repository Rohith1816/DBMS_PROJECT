package com.example.HM.models;

import java.sql.Date;

public class HostelUser {
    private String HostelName;
    private String status;
    private Date appliedDate;
    private Date closingDate;


    public HostelUser(String HostelName, String status, Date appliedDate, Date closingDate) {
        this.HostelName = HostelName;
        this.status = status;
        this.appliedDate = appliedDate;
        this.closingDate = closingDate;
    }


    public HostelUser() {
    }

    public String getHostelName() {
        return HostelName;
    }

    public void setHostelName(String hostelName) {
        HostelName = hostelName;
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

    @Override
    public String toString() {
        return "HostelUser{" +
                "HostelName='" + HostelName + '\'' +
                ", status='" + status + '\'' +
                ", appliedDate=" + appliedDate +
                ", closingDate=" + closingDate +
                '}';
    }
}



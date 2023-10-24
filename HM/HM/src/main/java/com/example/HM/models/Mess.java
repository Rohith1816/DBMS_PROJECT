package com.example.HM.models;

public class Mess {
    private int MessId;
    private String MessName;
    private String MessType;
    private String ContactNumber;

    public Mess() {

    }

    public Mess(int messId, String messName, String messType, String contactNumber) {
        MessId = messId;
        MessName = messName;
        MessType = messType;
        ContactNumber = contactNumber;
    }

    public int getMessId() {
        return MessId;
    }

    public void setMessId(int messId) {
        MessId = messId;
    }

    public String getMessName() {
        return MessName;
    }

    public void setMessName(String messName) {
        MessName = messName;
    }

    public String getMessType() {
        return MessType;
    }

    public void setMessType(String messType) {
        MessType = messType;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
}

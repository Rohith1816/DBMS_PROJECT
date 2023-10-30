package com.example.HM.models;

public class Mess {
    private int MessId;
    private String MessName;
    private String MessType;
    private String Address;
    private String ContactNumber;
    private int Capacity;

    public Mess() {

    }

    public Mess(int messId, String messName, String messType, String address, String contactNumber) {
        MessId = messId;
        MessName = messName;
        MessType = messType;
        Address = address;
        ContactNumber = contactNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
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

    @Override
    public String toString() {
        return "Mess{" +
                "MessId=" + MessId +
                ", MessName='" + MessName + '\'' +
                ", MessType='" + MessType + '\'' +
                ", Address='" + Address + '\'' +
                ", ContactNumber='" + ContactNumber + '\'' +
                ", Capacity=" + Capacity +
                '}';
    }
}

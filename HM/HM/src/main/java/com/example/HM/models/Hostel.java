package com.example.HM.models;

public class Hostel {
    private int HostelId;
    private String HostelName;
    private String Address;
    private String ContactNumber;
    private int Capacity;

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public int getHostelId() {
        return HostelId;
    }

    public void setHostelId(int hostelId) {
        HostelId = hostelId;
    }

    public String getHostelName() {
        return HostelName;
    }

    public void setHostelName(String hostelName) {
        HostelName = hostelName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public Hostel(int hostelId, String hostelName, String address, String contactNumber) {
        HostelId = hostelId;
        HostelName = hostelName;
        Address = address;
        ContactNumber = contactNumber;
    }

    public Hostel() {

    }

}

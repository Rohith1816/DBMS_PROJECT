package com.example.HM.models;


public class Payment {
    private int id;
    private int userId;
    private String PaymentFor;
    private String transactionId;
    private int amount;

    public Payment() {

    }

    public Payment(int userId, String paymentFor, String transactionId, int amount) {
        this.userId = userId;
        PaymentFor = paymentFor;
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPaymentFor() {
        return PaymentFor;
    }

    public void setPaymentFor(String paymentFor) {
        PaymentFor = paymentFor;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

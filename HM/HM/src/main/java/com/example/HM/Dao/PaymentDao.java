package com.example.HM.Dao;

import com.example.HM.models.Payment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentDao {
    JdbcTemplate jdbcTemplate;

    public PaymentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Payment> paymentRowMapper = (rs, rowNum) -> {
        Payment payment = new Payment();
        payment.setId(rs.getInt("id"));
        payment.setUserId(rs.getInt("userId"));
        payment.setPaymentFor(rs.getString("PaymentFor"));
        payment.setTransactionId(rs.getString("transactionId"));
        payment.setAmount(rs.getInt("amount"));
        return payment;
    };

    public int AddPayment(Payment payment){
//        String sql = "INSERT INTO payment(userId,PaymentFor,transactionId,amount) VALUES(?,?,?,?)";
//        jdbcTemplate.update(sql,payment.getUserId(),payment.getPaymentFor(),payment.getTransactionId(),payment.getAmount());
        int status ;
        String sql = "SELECT COUNT(*) FROM payment WHERE userId = ? AND PaymentFor = ?";
        int count = jdbcTemplate.queryForObject(sql,Integer.class,payment.getUserId(),payment.getPaymentFor());
        if(count==0){
//            sql = "INSERT INTO payment(userId,PaymentFor,transactionId,amount) VALUES(?,?,?,?)";
//            jdbcTemplate.update(sql,payment.getUserId(),payment.getPaymentFor(),payment.getTransactionId(),payment.getAmount());
            status = 1;
        }
        else{
            status = 0;
        }
        return status;
    }

    private final RowMapper<Payment> paymentRowMapper1 = (rs, rowNum) -> {
        Payment payment = new Payment();
        payment.setPaymentFor(rs.getString("PaymentFor"));
        payment.setTransactionId(rs.getString("transactionId"));
        payment.setAmount(rs.getInt("amount"));
        return payment;
    };

    public void AddPayment1(Payment payment){
        String sql = "INSERT INTO payment(userId,PaymentFor,transactionId,amount) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,payment.getUserId(),payment.getPaymentFor(),payment.getTransactionId(),payment.getAmount());
    }

    public List<Payment> getallpayments(int uuid){
        String sql = "SELECT * FROM payment WHERE userId = ?";
        return jdbcTemplate.query(sql,paymentRowMapper1,uuid);
    }



}

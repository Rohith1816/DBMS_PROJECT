package com.example.HM.Dao;

import com.example.HM.models.Payment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
        payment.setTransactionId(rs.getLong("transactionId"));
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
            sql = "INSERT INTO payment(userId,PaymentFor,transactionId,amount) VALUES(?,?,?,?)";
            jdbcTemplate.update(sql,payment.getUserId(),payment.getPaymentFor(),payment.getTransactionId(),payment.getAmount());
            status = 1;
        }
        else{
            status = 0;
        }
        return status;
    }

}

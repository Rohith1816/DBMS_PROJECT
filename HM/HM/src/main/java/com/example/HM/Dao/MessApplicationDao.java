package com.example.HM.Dao;

import com.example.HM.models.MessApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MessApplicationDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public MessApplicationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<MessApplication> messApplicationRowMapper = (rs, rowNum) -> {
        MessApplication messApplication = new MessApplication();
        messApplication.setApplicationId(rs.getInt("applicationId"));
        messApplication.setUserId(rs.getInt("userId"));
        messApplication.setMessId(rs.getInt("messId"));
        messApplication.setIs_active(rs.getBoolean("is_active"));
        messApplication.setAppliedDate(rs.getDate("appliedDate"));
        messApplication.setClosingDate(rs.getDate("closingDate"));
        return messApplication;
    };
    private final RowMapper<MessApplication> messApplicationRowMapper1 = (rs, rowNum) -> {
        MessApplication messApplication = new MessApplication();
        messApplication.setApplicationId(rs.getInt("applicationId"));
        return messApplication;
    };

    public int AddApplication(MessApplication messApplication){
        System.out.println("Entered Add application DAO");
        int uuid = messApplication.getUserId();
        System.out.println(uuid);
        String sql = "SELECT applicationId FROM messapplications WHERE is_active = TRUE AND userId = ?" ;
        MessApplication ma = null;
        try {
            ma = jdbcTemplate.queryForObject(sql,messApplicationRowMapper1,uuid);
        }
        catch (Exception e){
            ma = null;
        }
        System.out.println(ma);
        String countSql = "SELECT COUNT(*) FROM messapplications WHERE is_active = TRUE AND messId = ?";
        int count = jdbcTemplate.queryForObject(countSql,Integer.class,messApplication.getMessId());
        System.out.println("count :" + count);
        String messCapacity = "SELECT Capacity FROM mess WHERE MessId = ?";
        int capacity = jdbcTemplate.queryForObject(messCapacity,Integer.class,messApplication.getMessId());
        if(capacity == count){
            System.out.println("Mess if full");
            return -1;
        }
        else if(ma==null){
            System.out.println("Entered DAO null part");
            String sql1 = "INSERT INTO messapplications(userId,messId,is_active,appliedDate) VALUES(?,?,?,?)";
            jdbcTemplate.update(sql1,messApplication.getUserId(),messApplication.getMessId(),messApplication.isIs_active(),java.time.LocalDate.now());
            return 1;
        }
        else{
            return 0;
        }
    }

    public int withdrawApplication(int uid){
        String sql = "SELECT applicationId FROM messapplications WHERE is_active = TRUE AND userId = ?";
        MessApplication ma = null;
        try {
            ma = jdbcTemplate.queryForObject(sql,messApplicationRowMapper1,uid);
        }
        catch (Exception e){
            ma = null;
        }
        if(ma==null){
            return 0;
        }
        else{
            String sql1 = "UPDATE messapplications SET is_active = FALSE, closingDate = ? WHERE applicationId = ?";
            jdbcTemplate.update(sql1,java.time.LocalDate.now(),ma.getApplicationId());
            return 1;
        }
    }
}

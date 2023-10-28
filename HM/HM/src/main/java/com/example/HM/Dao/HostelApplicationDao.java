package com.example.HM.Dao;

import com.example.HM.models.HostelApplications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class HostelApplicationDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public HostelApplicationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<HostelApplications> hostelApplicationsRowMapper = (rs, rowNum) -> {
        HostelApplications hostelApplications = new HostelApplications();
        hostelApplications.setApplicationId(rs.getInt("applicationId"));
        hostelApplications.setUserId(rs.getInt("userId"));
        hostelApplications.setHostelId(rs.getInt("hostelId"));
        hostelApplications.setIs_active(rs.getBoolean("is_active"));
        hostelApplications.setAppliedDate(rs.getDate("appliedDate"));
        hostelApplications.setClosingDate(rs.getDate("closingDate"));
        return hostelApplications;
    };
    private final RowMapper<HostelApplications> hostelApplicationsRowMapper1 = (rs, rowNum) -> {
        HostelApplications hostelApplications = new HostelApplications();
        hostelApplications.setApplicationId(rs.getInt("applicationId"));
        return hostelApplications;
    };

    public boolean solve(int uuid){
        String sql = "SELECT COUNT(*) FROM Payment WHERE userId = ? AND PaymentFor = ?";
        int count = jdbcTemplate.queryForObject(sql,Integer.class,uuid,"Hostel");
        if(count==0){
            return false;
        }
        else{
            return true;
        }
    }
    public int AddApplication(HostelApplications hostelApplications){
        int uuid = hostelApplications.getUserId();
        boolean check = solve(uuid);
        if(check==false){
            System.out.println("Payment not made");
            return -2;
        }
        else{
            System.out.println("Payment made");
        }
        System.out.println(uuid);
        String sql = "SELECT applicationId FROM hostelapplications WHERE is_active = TRUE AND userId = ?" ;
        HostelApplications ha = null;
        try {
            ha = jdbcTemplate.queryForObject(sql,hostelApplicationsRowMapper1,uuid);
        }
        catch (Exception e){
            ha = null;
        }
        System.out.println(ha);
        String countSql = "SELECT COUNT(*) FROM hostelapplications WHERE is_active = TRUE AND hostelId = ?";
        int count = jdbcTemplate.queryForObject(countSql,Integer.class,hostelApplications.getHostelId());
        System.out.println("count :" + count);
        String hostelCapacity = "SELECT Capacity FROM hostel WHERE HostelId = ?";
        int capacity = jdbcTemplate.queryForObject(hostelCapacity,Integer.class,hostelApplications.getHostelId());
        if(capacity == count){
            System.out.println("Hostel if full");
            return -1;
        }
        else if(ha==null){
            System.out.println("Entered DAO null part");
            sql = "INSERT INTO HOSTELAPPLICATIONS(userId,hostelId,is_active,appliedDate,closingDate) VALUES (?,?,?,?,?)";
            jdbcTemplate.update(sql,hostelApplications.getUserId(),
                    hostelApplications.getHostelId(),
                    true,
                    java.time.LocalDate.now(),
                    null);
            return  1;
        }

        else{
            System.out.println("Entered DAO not null part");
            return  0;
        }
    }

    public int WithDrawApplication(int uid){
        String sql = "SELECT applicationId FROM hostelapplications WHERE is_active = TRUE AND userId = ?" ;
        HostelApplications ha = null;
        try {
            ha = jdbcTemplate.queryForObject(sql,hostelApplicationsRowMapper1,uid);
        }
        catch (Exception e){
            ha = null;
        }
        if(ha==null){
            return 0;
        }
        else{
            sql = "UPDATE hostelapplications SET is_active = FALSE , closingDate = ? WHERE applicationId = ?";
            jdbcTemplate.update(sql,java.time.LocalDate.now(),ha.getApplicationId());
            return 1;
        }
    }

    public List<HostelApplications> getAllApplications(String username){
        String sql = "SELECT * FROM hostelapplications where userId = (SELECT id FROM users WHERE username = ?)";
        return jdbcTemplate.query(sql,hostelApplicationsRowMapper,username);
    }

}

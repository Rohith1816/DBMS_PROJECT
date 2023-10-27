package com.example.HM.Dao;

import com.example.HM.models.Hostel;
import com.example.HM.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HostelDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Hostel> hostelRowMapper = (rs, rowNum) -> {
        Hostel hostel = new Hostel();
        hostel.setHostelId(rs.getInt("HostelId"));
        hostel.setHostelName(rs.getString("HostelName"));
        hostel.setAddress(rs.getString("Address"));
        hostel.setContactNumber(rs.getString("ContactNumber"));
        hostel.setCapacity(rs.getInt("Capacity"));
        return hostel;
    };
    private final RowMapper<Hostel> hostelRowMapper1 = (rs, rowNum) -> {
        Hostel hostel = new Hostel();
        hostel.setHostelName(rs.getString("HostelName"));
        return hostel;
    };

    public int getHostelIdFromSql(String hostelName){
        String sql = "SELECT * FROM HOSTEL WHERE HostelName = " + "'"+hostelName+"'";
        Hostel hostel = jdbcTemplate.queryForObject(sql,hostelRowMapper);
        return hostel.getHostelId();
    }

    public List<Hostel> getAllHostels(){
        String sql = "SELECT HostelName From Hostel";
        return jdbcTemplate.query(sql,hostelRowMapper1);
    }


}

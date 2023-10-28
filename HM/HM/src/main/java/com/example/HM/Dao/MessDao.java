package com.example.HM.Dao;

import com.example.HM.models.Mess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Mess> messRowMapper = (rs, rowNum) -> {
        Mess mess = new Mess();
        mess.setMessId(rs.getInt("MessId"));
        mess.setMessName(rs.getString("MessName"));
        mess.setMessType(rs.getString("MessType"));
        mess.setAddress(rs.getString("Address"));
        mess.setContactNumber(rs.getString("ContactNumber"));
        mess.setCapacity(rs.getInt("Capacity"));
        return mess;
    };
    private final RowMapper<Mess> messRowMapper1 = (rs, rowNum) -> {
        Mess mess = new Mess();
        mess.setMessName(rs.getString("MessName"));
        return mess;
    };

    public int getMessIdFromSql(String messName){
        String sql = "SELECT * FROM MESS WHERE MessName = " + "'"+messName+"'";
        Mess mess = jdbcTemplate.queryForObject(sql,messRowMapper);
        return mess.getMessId();
    }

    public List<Mess> getAllMess(){
        String sql = "SELECT MessName From Mess";
        return jdbcTemplate.query(sql,messRowMapper1);
    }

}

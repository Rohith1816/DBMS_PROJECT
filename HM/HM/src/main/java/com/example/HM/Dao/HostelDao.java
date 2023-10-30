package com.example.HM.Dao;

import com.example.HM.models.Hostel;
import com.example.HM.models.User;
import com.example.HM.models.Vacancy;
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
        hostel.setCapacity(rs.getInt("Capacity"));
        return hostel;
    };

    private final RowMapper<Vacancy> VacancyRowMapper = (rs, rowNum) -> {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(rs.getString("Name"));
        vacancy.setCapacity(rs.getInt("Capacity"));
        vacancy.setRemainingVacancy(rs.getInt("Vacancy"));
        return vacancy;
    };

    public int getHostelIdFromSql(String hostelName){
        String sql = "SELECT * FROM HOSTEL WHERE HostelName = " + "'"+hostelName+"'";
        Hostel hostel = jdbcTemplate.queryForObject(sql,hostelRowMapper);
        return hostel.getHostelId();
    }

    public List<Vacancy> getAllHostels(){
//        String s1 = "SELECT DISTINCT h.HostelName AS Name, h.capacity AS Capacity, " +
//                "(h.capacity - (SELECT COUNT(a) FROM HostelApplication a WHERE a.isActive = 1 AND a.hostel = h)) AS Vacancy " +
//                "FROM Hostel h ORDER BY h.hostelName";
        String s2 = "select distinct hostel.hostelname as Name, Capacity as Capacity , hostel.Capacity-( select count(*) from hostelapplications where is_active = 1 AND hostelapplications.hostelId = hostel.HostelId) as 'Vacancy'\n" +
                "from hostel,hostelapplications order by hostel.hostelname;";
//        String sql = "SELECT HostelName,Capacity From Hostel";
        return jdbcTemplate.query(s2,VacancyRowMapper);
    }


}

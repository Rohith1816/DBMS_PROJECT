package com.example.HM.Dao;

import com.example.HM.models.Mess;
import com.example.HM.models.Vacancy;
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

    private final RowMapper<Vacancy> VacancyRowMapper = (rs, rowNum) -> {
        Vacancy vacancy = new Vacancy();
        vacancy.setName(rs.getString("Name"));
        vacancy.setCapacity(rs.getInt("Capacity"));
        vacancy.setRemainingVacancy(rs.getInt("Vacancy"));
        return vacancy;
    };

    public List<Vacancy> getAllMess(){
        String sql = "SELECT MessName From Mess";
        String query = "select distinct mess.messname as Name, Capacity as Capacity , \n" +
                "mess.Capacity-( select count(*) from messapplications where is_active = 1 AND messapplications.messId = mess.messId)\n" +
                " as 'Vacancy'\n" +
                "from mess,messapplications order by mess.messname;";
        return jdbcTemplate.query(query,VacancyRowMapper);
    }

}

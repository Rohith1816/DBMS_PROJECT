package com.example.HM.Dao;

import com.example.HM.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void AddUser(User user){
        String sql_query = "INSERT INTO users (id,username,first_name,last_name,email,password) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql_query,
                user.getId(),
                user.getUsername(),
                user.getFirst_name(),
                user.getLast_name(),
                user.getEmail(),
                user.getPassword()
        );
    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    public User getUserByUsername(String username){
        String sql = "Select * from users where username = "+"'"+username+"'";
        return jdbcTemplate.queryForObject(sql, userRowMapper);
    }

    public List<User> getAllUsers(){
        String sqlQuery = "SELECT * FROM USERS";
        return jdbcTemplate.query(sqlQuery,userRowMapper);
    }




}

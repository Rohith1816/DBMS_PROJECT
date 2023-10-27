package com.example.HM.Dao;

import com.example.HM.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
//    private String loggedUser;

//    public String getLoggedUser() {
//        return loggedUser;
//    }

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        return user;
    };

    public User getUserByUsername(String username){
//        this.loggedUser = username;
        String sql = "Select * from users where username = "+"'"+username+"'";
        return jdbcTemplate.queryForObject(sql, userRowMapper);
    }

    public int AddUser(User user){

        System.out.println("Entered DAO");
        User user1 = null;
        try{
            user1 = getUserByUsername(user.getUsername());
        }
        catch (Exception e){
           user1 = null;
        }
        String sql_query = "INSERT INTO users (username,first_name,last_name,email,password) VALUES (?,?,?,?,?)";
        if(user1==null){
            jdbcTemplate.update(sql_query,
                    user.getUsername(),
                    user.getFirst_name(),
                    user.getLast_name(),
                    user.getEmail(),
                    user.getPassword()
            );
            return 1;
        }
        else{
            System.out.println("Username Already Exists");
            return 0;
        }
    }



    public List<User> getAllUsers(){
        String sqlQuery = "SELECT * FROM USERS";
        return jdbcTemplate.query(sqlQuery,userRowMapper);
    }

    public User getDetailsofUser(String username){
        String sql = "Select * from users where username = "+"'"+username+"'";
        return jdbcTemplate.queryForObject(sql,userRowMapper);
    }




}

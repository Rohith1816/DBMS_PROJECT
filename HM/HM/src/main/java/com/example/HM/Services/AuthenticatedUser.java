package com.example.HM.Services;

import com.example.HM.Dao.UserDao;
import com.example.HM.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUser {
    private final UserDao userDao;

    @Autowired
    public AuthenticatedUser(UserDao userDao){
        this.userDao = userDao;
    }

    public User getAuthenticatedUser(UserDetails userDetails){
        try{
            return userDao.getUserByUsername(userDetails.getUsername());
        }
        catch (Exception e){
            return null;
        }
    }

}

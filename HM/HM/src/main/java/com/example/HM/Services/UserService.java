package com.example.HM.Services;

import com.example.HM.Dao.UserDao;
import com.example.HM.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("puknak")
public class UserService implements UserDetailsService{
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        if (user == null) throw new UsernameNotFoundException("Not found");
        return new SecurityUserDetails(user);
    }
}

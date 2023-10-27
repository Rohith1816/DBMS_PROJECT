package com.example.HM.Controller;

import com.example.HM.Dao.HostelApplicationDao;
import com.example.HM.Dao.UserDao;
import com.example.HM.Services.AuthenticatedUser;
import com.example.HM.models.HostelApplications;
import com.example.HM.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    UserDao userdao;
    HostelApplicationDao hostelApplicationDao;
    AuthenticatedUser authenticatedUser;

    public HomeController(UserDao userdao,HostelApplicationDao hostelApplicationDao,AuthenticatedUser authenticatedUser) {
        this.userdao = userdao;
        this.hostelApplicationDao = hostelApplicationDao;
        this.authenticatedUser = authenticatedUser;
    }

    @RequestMapping(path = {"/home"})
    public String home(){
        return "home";
    }
    @RequestMapping(path="/")
    public String autherizationPage(){
        return "lockPage";
    }

    @ResponseBody
    @RequestMapping(path="/history")
    public List<HostelApplications> history(@AuthenticationPrincipal UserDetails userDetails){
//        String username = userdao.getLoggedUser();
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        String username = u.getUsername();
        return hostelApplicationDao.getAllApplications(username);
    }

}
//getLoggedUser

package com.example.HM.Controller;

import com.example.HM.Dao.HostelApplicationDao;
import com.example.HM.Dao.HostelDao;
import com.example.HM.Dao.UserDao;
import com.example.HM.Services.AuthenticatedUser;
import com.example.HM.models.HostelApplications;
import com.example.HM.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HostelApplicationController {

    private HostelApplicationDao hostelApplicationDao;
    private UserDao userdao;
    private HostelDao hostelDao;
    private AuthenticatedUser authenticatedUser;

    @Autowired
    public HostelApplicationController(HostelApplicationDao hostelApplicationDao, UserDao userdao, HostelDao hostelDao,AuthenticatedUser authenticatedUser) {
        this.hostelApplicationDao = hostelApplicationDao;
        this.userdao = userdao;
        this.hostelDao = hostelDao;
        this.authenticatedUser = authenticatedUser;
    }

    @ResponseBody
    @PostMapping(path={"/submit_application"})
    public String addToHostelApplication(@RequestParam("hostel") String hostelName, @AuthenticationPrincipal UserDetails userDetails){
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        HostelApplications hostelApplications = new HostelApplications();
//        User u = userdao.getUserByUsername(userdao.getLoggedUser());
        hostelApplications.setUserId(u.getId());
        hostelApplications.setIs_active(true);
        int hostelId = hostelDao.getHostelIdFromSql(hostelName);
        hostelApplications.setHostelId(hostelId);
//        System.out.println(hostelName);
//        System.out.println(hostelId);
//        System.out.println(u.getId());
        int status = hostelApplicationDao.AddApplication(hostelApplications);
        if(status==1)
        {
            return "Application added successfully";
        }
        else if(status==-1)
        {
            System.out.println("Hostel is full");
        }
        else
        {
            System.out.println("Application already exists");
        }
        return "moon";
    }

    @GetMapping(path={"/withdraw_application"})
    public String withdrawApplication(@AuthenticationPrincipal UserDetails userDetails){
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        int userId = u.getId();
        int status = hostelApplicationDao.WithDrawApplication(userId);
        if(status==1)
            System.out.println("Application withdrawn successfully");
        else
            System.out.println("Application does not exist");
        return "redirect:/home";
    }




}

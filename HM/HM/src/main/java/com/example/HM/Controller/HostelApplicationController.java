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
import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HostelApplicationController {

    private HostelApplicationDao hostelApplicationDao;
    private HostelDao hostelDao;
    private AuthenticatedUser authenticatedUser;

    @Autowired
    public HostelApplicationController(HostelApplicationDao hostelApplicationDao, HostelDao hostelDao,AuthenticatedUser authenticatedUser) {
        this.hostelApplicationDao = hostelApplicationDao;
        this.hostelDao = hostelDao;
        this.authenticatedUser = authenticatedUser;
    }


    @PostMapping(path={"/submit_application_hostel"})
//    @ResponseBody
    public String addToHostelApplication(@RequestParam("hostel") String hostelName, @AuthenticationPrincipal UserDetails userDetails, Model model){
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        HostelApplications hostelApplications = new HostelApplications();
//        User u = userdao.getUserByUsername(userdao.getLoggedUser());
        hostelApplications.setUserId(u.getId());
        hostelApplications.setIs_active(true);
        int hostelId = hostelDao.getHostelIdFromSql(hostelName);
        hostelApplications.setHostelId(hostelId);
        System.out.println(hostelName);
//        System.out.println(hostelId);
//        System.out.println(u.getId());
        String response;
        int status = hostelApplicationDao.AddApplication(hostelApplications);
        if(status==-2){
            response = "Payment not made";
            model.addAttribute("alertMessage", response);
            return "neccessaypayment";
        }
        else if(status==1)
        {
           response = "Application added successfully";
        }
        else if(status==-1)
        {
            response = "Hostel is full";
        }
        else
        {
            response = "Application already exists";
        }

        model.addAttribute("alertMessage", response);

        return "message";
    }

    @GetMapping(path={"/withdraw_application_hostel"})
    public String withdrawApplication(@AuthenticationPrincipal UserDetails userDetails,Model model){
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        int userId = u.getId();
        int status = hostelApplicationDao.WithDrawApplication(userId);
        String response;
        if(status==1)
        {
            response = "Application withdrawn successfully";
        }
        else
        {
            response = "Application does not exist";
        }
        model.addAttribute("alertMessage", response);
        return "message";
    }
}

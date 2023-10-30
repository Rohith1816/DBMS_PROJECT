package com.example.HM.Controller;

import com.example.HM.Dao.MessApplicationDao;
import com.example.HM.Dao.MessDao;
import com.example.HM.Services.AuthenticatedUser;
import com.example.HM.models.MessApplication;
import com.example.HM.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessApplicationController {
    private MessApplicationDao messApplicationDao;
    private MessDao messDao;
    private AuthenticatedUser authenticatedUser;

    public MessApplicationController(MessApplicationDao messApplicationDao, MessDao messDao, AuthenticatedUser authenticatedUser) {
        this.messApplicationDao = messApplicationDao;
        this.messDao = messDao;
        this.authenticatedUser = authenticatedUser;
    }

    @PostMapping(path={"/submit_application_mess"})
    public String submitApplication(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("mess") String messName, Model model){
//        System.out.println("Entered submit application controller");
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        MessApplication messApplication = new MessApplication();
        messApplication.setUserId(u.getId());
        messApplication.setIs_active(true);
        int messId = messDao.getMessIdFromSql(messName);
        messApplication.setMessId(messId);
        String response;
        int status = messApplicationDao.AddApplication(messApplication);
        if(status==-2){
            response = "Payment not made";
            model.addAttribute("alertMessage", response);
            return "neccessaypayment";
        }
        else
        if(status==1)
        {
            response = "Application added successfully";
        }
        else if(status==-1)
        {
            response = "Mess is full";
        }
        else
        {
            response = "Active application already exists";
        }
        model.addAttribute("alertMessage", response);
        return "message";
    }

    @GetMapping(path={"/withdraw_application_mess"})
    public String withdrawApplication(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        int userId = u.getId();
        String response;
        int status = messApplicationDao.withdrawApplication(userId);
        if(status==1)
        {
            response = "Application withdrawn successfully";
        }
        else
        {
            response = "No active application exists";
        }
        model.addAttribute("alertMessage", response);
        return "message";
    }
}

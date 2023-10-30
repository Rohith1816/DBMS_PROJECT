package com.example.HM.Controller;

import com.example.HM.Dao.HostelApplicationDao;
import com.example.HM.Dao.MessApplicationDao;
import com.example.HM.Dao.PaymentDao;
import com.example.HM.Dao.UserDao;
import com.example.HM.Services.AuthenticatedUser;
import com.example.HM.models.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private UserDao userdao;
    private HostelApplicationDao hostelApplicationDao;
    private AuthenticatedUser authenticatedUser;
    private MessApplicationDao messApplicationDao;
    private PaymentDao paymentDao;

    public HomeController(UserDao userdao, HostelApplicationDao hostelApplicationDao, AuthenticatedUser authenticatedUser, MessApplicationDao messApplicationDao, com.example.HM.Dao.PaymentDao paymentDao) {
        this.userdao = userdao;
        this.hostelApplicationDao = hostelApplicationDao;
        this.authenticatedUser = authenticatedUser;
        this.messApplicationDao = messApplicationDao;
        this.paymentDao = paymentDao;
    }

    @RequestMapping(path = {"/home"})
    public String home(Model model,@AuthenticationPrincipal UserDetails userDetails){
        User user = authenticatedUser.getAuthenticatedUser(userDetails);
        String Name = user.getFirst_name() + " " + user.getLast_name();
        model.addAttribute("Name",Name);
        return "home";
    }

    @RequestMapping(path="/")
    public String authorizationPage(){
        return "lockPage";
    }

//    @ResponseBody
    @RequestMapping(path="/history")
    public String history(@AuthenticationPrincipal UserDetails userDetails, Model model){
//        String username = userdao.getLoggedUser();
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        String username = u.getUsername();
//        return hostelApplicationDao.getAllApplications(u.getId());
        model.addAttribute("hostelUserList",hostelApplicationDao.getAllApplications(u.getId()));
        model.addAttribute("messUserList",messApplicationDao.getAllApplications(u.getId()));
        model.addAttribute("paymentList", paymentDao.getallpayments(u.getId()));
        return "history";
    }

    @RequestMapping(path="/profile")
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model){
        User u = authenticatedUser.getAuthenticatedUser(userDetails);
        model.addAttribute("user",u);
        return "profile";
    }



}
//getLoggedUser

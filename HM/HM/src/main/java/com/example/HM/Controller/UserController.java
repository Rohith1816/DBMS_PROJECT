package com.example.HM.Controller;
import com.example.HM.Dao.UserDao;
import com.example.HM.Services.AuthenticatedUser;
import com.example.HM.Services.SecurityUserDetails;
import com.example.HM.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserDao userdao;
    private AuthenticatedUser authenticatedUser;

    @Autowired
    public UserController(UserDao userdao,AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        this.userdao = userdao;
    }

    @GetMapping(path="/register")
    public String Register(){

        return "register";
    }
    @PostMapping(path="/register")
    public String RegisterUser(@RequestParam("username") String username,
                               @RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,Model model){
        User user = new User(username,firstName,lastName,email,password);
        int status = userdao.AddUser(user);
        model.addAttribute("status","Username Already Exists");
        if(status==1)
            return "login";
        else
            return "registrationfail";
    }
    @GetMapping(path="/signin")
    public String usersignIn(){

        return "login";
    }
    @RequestMapping(path={"/allusers"})
    public String getall(Model model){
         List<User> user= userdao.getAllUsers();
        model.addAttribute("users",user);
        return "users";
    }

    @RequestMapping(path={"/about"})
    public String printdetails(Model model, @AuthenticationPrincipal UserDetails userDetails){
        User user =  authenticatedUser.getAuthenticatedUser(userDetails);
//        User user = userdao.getDetailsofUser(userdao.getLoggedUser());
        model.addAttribute(user);
        return "about";
    }

//    @GetMapping("/signup")
//    public String register(Model model){
//        User user = new User();
////        model.addAttribute("")
//        return
//    }

}

package com.example.HM.Controller;
import com.example.HM.Dao.UserDao;
import com.example.HM.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserDao userdao;

    @Autowired
    public UserController(UserDao userdao){
        this.userdao = userdao;
    }

    @GetMapping(path="/register")
    public String Register(){

        return "register";
    }
    @PostMapping(path="/register")
    public String RegisterUser(@RequestParam("id") Long id,
                               @RequestParam("username") String username,
                               @RequestParam("first_name") String firstName,
                               @RequestParam("last_name") String lastName,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password){
        User user = new User(id,username,firstName,lastName,email,password);
        userdao.AddUser(user);
        return "home";
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

//    @GetMapping("/signup")
//    public String register(Model model){
//        User user = new User();
////        model.addAttribute("")
//        return
//    }

}

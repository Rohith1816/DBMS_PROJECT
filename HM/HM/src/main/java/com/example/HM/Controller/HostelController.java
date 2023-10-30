package com.example.HM.Controller;

import com.example.HM.Dao.HostelDao;
import com.example.HM.Dao.UserDao;
import com.example.HM.models.Hostel;
import com.example.HM.models.HostelApplications;
import com.example.HM.models.User;
import com.example.HM.models.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HostelController {

    private HostelDao hostelDao;

    @Autowired
    public HostelController(HostelDao hostelDao) {
        this.hostelDao = hostelDao;
    }

    @GetMapping(path={"/hostels"})
    public String gethostels(Model model){
        List<Vacancy> hostelsList = hostelDao.getAllHostels();
        System.out.println(hostelsList);
        model.addAttribute("hostels",hostelsList);
        return "hostels";
    }


}

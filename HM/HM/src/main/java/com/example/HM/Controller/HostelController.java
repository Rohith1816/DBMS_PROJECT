package com.example.HM.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HostelController {
    @RequestMapping(path={"/hostels"})
    @ResponseBody
    public String gethostels(){
        return "Rohith";
    }
}

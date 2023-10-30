package com.example.HM.Controller;

import com.example.HM.models.Mess;
import com.example.HM.models.Vacancy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.HM.Dao.MessDao;

import java.util.List;

@Controller
public class MessController {
    private MessDao messDao;

    public MessController(MessDao messDao){
        this.messDao = messDao;
    }

    @GetMapping(path={"/mess"})
    public String getMess(Model model){
        List<Vacancy> messList = messDao.getAllMess();
        model.addAttribute("messes",messList);
        return "mess";
    }
}

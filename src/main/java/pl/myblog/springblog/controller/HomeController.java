package pl.myblog.springblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")            // mapowany adres
    public String home(){       // nazwa metody wywoływanej dla URL "/"
        return "index";         // nazwa zwracanego widoku HTML
    }
}

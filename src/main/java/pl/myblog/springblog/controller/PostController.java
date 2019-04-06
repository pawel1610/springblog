package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.myblog.springblog.service.UserService;


@Controller
public class PostController {
    UserService userService;

    @Autowired
    public PostController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }
        return "index";

    }
}

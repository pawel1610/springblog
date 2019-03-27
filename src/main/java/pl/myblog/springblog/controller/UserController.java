package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.myblog.springblog.model.dto.UserDto;
import pl.myblog.springblog.service.UserService;

import javax.validation.Valid;

@Controller
public class UserController {
    UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // przejście na stronę formularza i przesłanie parametrów metodą POST
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserDto());
        return "registerForm";
    }
    // odbiór prarametrów przesłanych przez formularz metodą POST
    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getRawFieldValue("name"));
            return "registerForm";
        }
        // dodawanie usera do DB
        System.out.println("Zarejestrowano: " + userService.addUser(userDto));
        return "redirect:/";
    }
    // logowanie -> wyświetlenie widoku HTML i oddanie w zarządzanie WebSecurityConfig
    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }
}

package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.myblog.springblog.model.User;
import pl.myblog.springblog.service.MainService;
import java.util.List;

@RestController
public class MainController {

    MainService mainService;
    @Autowired
    public MainController(MainService mainService) {
        this.mainService = mainService;
    }
    @GetMapping("/")
    public String home(){
        return "Hello my BLOG!";
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return mainService.getAllUsers();
    }
    @GetMapping("/users/{email}")
    public User getUserByEmail(@PathVariable("email") String email){
        return mainService.getUserByEmail(email);
    }
    @GetMapping("/users/count")
    public Long countAllUsers(){
        return mainService.countAllUsers();
    }
    @GetMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") Long id){
        mainService.updateUserActivityById(id);
        return "Zmieniono aktywność";
    }
    @GetMapping("/login/{email}/{password}")
    public String loginCheck(
            @PathVariable("email") String email,
            @PathVariable("password") String password){
        // zwraca usera lub null
        User user = mainService.logIn(email, password);
        return (user != null) ? user.toString() : "Błąd logowania" ;
    }
    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        mainService.deleteUserUserById(id);
        return "Usunięto";
    }
}

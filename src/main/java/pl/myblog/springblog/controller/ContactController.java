package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.myblog.springblog.model.Contact;
import pl.myblog.springblog.service.ContactService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/contact")
    public String contact(Model model, Authentication auth){
        model.addAttribute("auth", auth);
        model.addAttribute("contact", new Contact());
        return "contact";
    }
    @PostMapping("/send")
    public String sendMessage(@Valid @ModelAttribute Contact contact, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
        } else {
            model.addAttribute("isSent", true);
            contactService.add(contact);
        }
        return "contact";
    }
}

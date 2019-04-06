package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.myblog.springblog.model.Contact;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.model.utiils.CategoryEnum;
import pl.myblog.springblog.service.PostService;
import pl.myblog.springblog.service.UserService;

import java.util.*;


@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;


    @GetMapping("/")
    public String home(Model model, Authentication auth) {
        model.addAttribute("auth", auth);
        model.addAttribute("categoriesList", postService.getCategories());
        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
        } else {
            model.addAttribute("isAdmin", false);
        }

        model.addAttribute("allPosts",postService.getAllPost());
        System.out.println(postService.getAllPost());
        return "index";

    }

    @GetMapping("/addpost")
    public String addPost(Model model, Authentication auth){
        Post newPost = new Post();
        newPost.setUser(userService.getUserById(auth));
        List<CategoryEnum> categories = Arrays.asList(CategoryEnum.values());
        model.addAttribute("categories", categories);
        System.out.println(categories);
        model.addAttribute("auth",auth);
        model.addAttribute(newPost);
        return "addPostForm";

    }
    @PostMapping("/savePost")
    public String savedPost(@ModelAttribute Post post, Model model, Authentication auth){
        model.addAttribute("isSave", true);
        postService.savePost(post, userService.getUserById(auth));
        return "index";
    }

    @GetMapping("/showPost")
    public String showPost(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute(postService.findByID(id));
        return "postDetails";

    }










}

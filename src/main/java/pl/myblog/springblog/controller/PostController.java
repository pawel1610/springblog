package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.myblog.springblog.model.Comment;
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
        model.addAttribute("categoriesList", postService.getActiveCategories());
        if (auth != null) {
            model.addAttribute("isAdmin", userService.isAdmin(auth));
            System.out.println(userService.isAdmin(auth));
            model.addAttribute("user", userService.getUserById(auth));
            model.addAttribute("loggedId", userService.getUserByEmail(auth).getId());

        } else {
            model.addAttribute("isAdmin", false);
        }

        model.addAttribute("postsByDate",postService.getPostsOrderByDate());
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
        return "redirect:";
    }

    @GetMapping("/delete")
    public String deletePost(@RequestParam(name = "id") Long id) {
        Post post = postService.findByID(id);
        postService.delete(post);
        return "redirect:";
    }

    @GetMapping("/showPost")
    public String showPost(@RequestParam(name = "id") Long id, Model model) {
        Post post = postService.findByID(id);
        Comment comment = new Comment();
        post.addCommentToPost(comment);
        model.addAttribute(comment);
        model.addAttribute(post);
        System.out.println(postService.findByID(id));
        return "postDetails";
    }
    @GetMapping("/editPost")
    public String editPost(@RequestParam(name = "id") Long id, Model model){
        Post post = postService.findByID(id);
        List<CategoryEnum> categories = Arrays.asList(CategoryEnum.values());
        model.addAttribute("categories", categories);
        model.addAttribute("post", post);
        return "addPostForm";

    }







}

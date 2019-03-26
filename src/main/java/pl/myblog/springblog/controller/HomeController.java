package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.service.PostService;
import java.util.List;

@Controller
public class HomeController {
    PostService postService;
    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/")            // mapowany adres
    public String home(Model model){       // nazwa metody wywoływanej dla URL "/"
        List<Post> posts = postService.getAllPosts();
        // przekazanie obiektu posts do html i w html też będzie nazywał się posts
        model.addAttribute("posts", posts);
        return "index";         // nazwa zwracanego widoku HTML

    }
    @GetMapping("/allposts/{id}")
    public String getOnePost(
            @PathVariable("id") Long id,
            Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
}

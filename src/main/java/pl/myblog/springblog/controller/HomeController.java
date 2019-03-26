package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.service.PostService;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    PostService postService;
    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/")                                            // mapowany adres
    public String home(Model model){                            // nazwa metody wywoływanej dla URL "/"
        List<Post> posts = postService.getAllPosts();
        List<Post> sortedPosts = posts
                                    .stream()                                                               // zamiana kolekcji na strumień
                                    .sorted((p1, p2) -> p2.getDate_added().compareTo(p1.getDate_added()))   // sortowanie po dacie DESC
                                    .collect(Collectors.toList());                                          // zapis do kolekcji posortowanych postów
        // II metoda
        // List<Post> sortedPosts = postService.getAllPosts()
        //                                          .stream()
        //                                          .sorted(Comparator.comparing(Post::getDate_added).reversed())
        //                                          .collect(Collectors.toList());
        model.addAttribute("posts", sortedPosts);
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

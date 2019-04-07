package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.myblog.springblog.model.Comment;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.service.CommentService;
import pl.myblog.springblog.service.PostService;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;


    @PostMapping(value = "/saveComment/{id}")
    public String saveComment(@PathVariable(name = "id") Long id,Comment comment) {
        comment.setPost(postService.findByID(id));
        commentService.saveComment(comment);
        return "redirect:/";
    }

    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam(name = "id") Long id) {
        Comment comment = commentService.findById(id);
        commentService.delete(comment);
        return "redirect:/";
    }
}

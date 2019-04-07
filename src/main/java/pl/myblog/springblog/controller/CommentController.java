package pl.myblog.springblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.myblog.springblog.service.CommentService;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
}

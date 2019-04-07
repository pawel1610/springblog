package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Comment;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.repository.CommentRpository;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRpository commentRpository;

    public List<Comment> getCommentsByPostId(Long id){
        List <Comment> commentListByPostId = commentRpository.findAllByPost_id(id);
        return commentListByPostId;
    }

    public void saveComment (Comment comment){
        commentRpository.save(comment);
    }

    public  Comment findById(Long id){
        Comment comment = commentRpository.findById(id).get();
        return comment;
    }

    public void delete(Comment comment){
        commentRpository.delete(comment);
    }
}

package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.model.User;
import pl.myblog.springblog.model.utiils.CategoryEnum;
import pl.myblog.springblog.repository.PostRepository;
import pl.myblog.springblog.repository.UserRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    public List<Post> getAllPost(){
        List<Post> allPosts =  postRepository.findAll();
        return allPosts;
    }

    public void savePost(Post post, User user){
        user.addPost(post);
        post.setUser(user);
        postRepository.save(post);
    }

    public Post findByID(Long id){
        Post post = postRepository.findById(id).get();
        return post;
    }



}

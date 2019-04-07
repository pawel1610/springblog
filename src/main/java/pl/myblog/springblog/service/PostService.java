package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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

    public void saveEditedPost(Post post,User user){
        post.setUser(user);
        postRepository.save(post);
    }

    public Post findByID(Long id){
        Post post = postRepository.findById(id).get();
        return post;
    }
    public List<CategoryEnum> getActiveCategories() {
        List<CategoryEnum> categoriesList = postRepository.getActiveCategories();
        return categoriesList;
    }
    public void delete(Post post){
        postRepository.delete(post);
    }
    public List<Post> getPostsOrderByDate(){
        List<Post> postsByDate =  postRepository.getPostsOrderByDate();
        return postsByDate;
    }
}

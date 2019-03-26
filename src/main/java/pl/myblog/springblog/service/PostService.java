package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.repository.PostRepository;
import java.util.List;

@Service
public class PostService {
    PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    // metoda zwracająca wszystkie posty
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }


}

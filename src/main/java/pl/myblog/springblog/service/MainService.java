package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.model.PostCategory;
import pl.myblog.springblog.model.User;
import pl.myblog.springblog.repository.PostRepository;
import pl.myblog.springblog.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MainService {
    // Dependency Injection
    UserRepository userRepository;
    PostRepository postRepository;
    @Autowired
    public MainService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();    // SELECT * FROM user;
    }
    // END-POINT zwracający użytkownika o zadanym adresie email
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }
    // END-POINT zwracający liczbę użytkowników
    public Long countAllUsers(){
        return userRepository.count();
    }
    // END-POINT zmieniający aktywność użytkownika
    public void updateUserActivityById(Long id){
        // SELECT * FROM user Where id = ?
        User user = userRepository.getOne(id);
        user.setActive(!user.getActive());
        // UPDATE user
        userRepository.save(user);
    }
    // END-POINT zwracający wynik logowania
    public User logIn(String email, String password){
        return userRepository.findByEmailAndPassword(email,password);
    }
    // END-POINT usuwający użytkownika po id
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }
    // END-POINT utworzenie nowego posta
    public void addPost(Long id, String title, String content){
        // szukamy usera po ID
        User user = userRepository.getOne(id);
        // utwórz obiekt posta
        Post post = new Post(title, content, PostCategory.PROGRAMOWANIE, user);
        // dodaj posta do zbioru postów obkietu user
        user.addPost(post);
        postRepository.save(post);
    }

}

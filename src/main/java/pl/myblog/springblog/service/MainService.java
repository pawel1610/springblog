package pl.myblog.springblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.myblog.springblog.model.User;
import pl.myblog.springblog.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MainService {
    // Dependency Injection
    UserRepository userRepository;

    @Autowired      // wstrzyknięcie zależności przez konstruktor
    public MainService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public void deleteUserUserById(Long id){
        userRepository.deleteById(id);
    }

}

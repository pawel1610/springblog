package pl.myblog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.myblog.springblog.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // SELECT * FROM user WHERE email = ?
    User findByEmail(String email);
    // SELECT * FROM user WHERE email = ? AND password = ?
    User findByEmailAndPassword(String emial, String password);
}

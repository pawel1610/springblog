package pl.myblog.springblog.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;


@NoArgsConstructor
@Entity                                                 // tworzy tabelkę w DB
public class User {
    @Id                                                 // PK
    @GeneratedValue(strategy = GenerationType.AUTO)     // AI
    private Long id;
//    @NotNull                                            // NN
    private String name;
//    @NotNull
    private String lastname;
//    @Email                                              // Email validation
//    @NotNull
    private String email;
//    @Length(min = 6)                                    // min 6 znaków
//    @Pattern(regexp = "([A-Z]+.*[0-9]+|[0-9]+.*[A-Z])") // co najmniej 1XCL 1XDIGIT
    private String password;
    private Boolean active = true;
    private LocalDateTime registered_date = LocalDateTime.now();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    List<Post> posts = new ArrayList<>();

    public void addPost(Post post){
        this.posts.add(post);
    }


    @ManyToMany
    @JoinTable(
            name = "user_role",                                 // nawa tabelki N:M
            joinColumns = @JoinColumn(name = "user_id"),        // nazwa kolumny 1
            inverseJoinColumns = @JoinColumn(name = "role_id")) // nazwa kolumny 2
    private Set<Role> roles = new HashSet<>();                          // zbiór ról


    // metoda dodająca rolę dla użytkownika
    public void addRole(Role role){
        this.roles.add(role);
    }

    public User(String name, String lastname, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String name, String lastname, String email, String password, Boolean active, LocalDateTime registered_date, List<Post> posts, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.active = active;
        this.registered_date = registered_date;
        this.posts = posts;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", registered_date=" + registered_date +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(LocalDateTime registered_date) {
        this.registered_date = registered_date;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}

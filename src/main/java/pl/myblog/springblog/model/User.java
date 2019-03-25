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
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity                                                 // tworzy tabelkę w DB
public class User {
    @Id                                                 // PK
    @GeneratedValue(strategy = GenerationType.AUTO)     // AI
    private Long id;
    @NotNull                                            // NN
    private String name;
    @NotNull
    private String lastname;
    @Email                                              // Email validation
    @NotNull
    private String email;
    @Length(min = 6)                                    // min 6 znaków
    @Pattern(regexp = "([A-Z]+.*[0-9]+|[0-9]+.*[A-Z])") // co najmniej 1XCL 1XDIGIT
    private String password;

    private Boolean active = true;
    private LocalDateTime registered_date = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "user_role",                                 // nawa tabelki N:M
            joinColumns = @JoinColumn(name = "user_id"),        // nazwa kolumny 1
            inverseJoinColumns = @JoinColumn(name = "role_id")) // nazwa kolumny 2
    Set<Role> roles = new HashSet<>();                          // zbiór ról
}

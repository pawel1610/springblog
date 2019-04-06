package pl.myblog.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pl.myblog.springblog.model.utiils.CategoryEnum;
import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Type(type = "text")
    private String contetnt;

    @ManyToOne
    @JoinColumn
    private User user;


    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private LocalDateTime postDate = LocalDateTime.now();

    public Post(String title, String contetnt, User user, CategoryEnum category) {
        this.title = title;
        this.contetnt = contetnt;
        this.user = user;
        this.category = category;
    }
}

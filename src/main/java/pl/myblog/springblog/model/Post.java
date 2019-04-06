package pl.myblog.springblog.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import pl.myblog.springblog.model.utiils.CategoryEnum;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "post")
    List<Comment> commentList = new ArrayList<>();


    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private LocalDateTime postDate = LocalDateTime.now();

    public Post(String title, String content, User user, CategoryEnum category) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.category = category;
    }
}

package pl.myblog.springblog.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Type(type = "text")
    private String message;
    private String author;
    private LocalDateTime commentDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn
    private Post post;
}

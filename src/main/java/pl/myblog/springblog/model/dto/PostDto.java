package pl.myblog.springblog.model.dto;

import lombok.Data;
import org.hibernate.annotations.Type;
import pl.myblog.springblog.model.PostCategory;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class PostDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Content is required")
    private String content;
    @Enumerated
    private PostCategory category;
}

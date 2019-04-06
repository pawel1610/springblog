package pl.myblog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.myblog.springblog.model.Post;
import pl.myblog.springblog.model.utiils.CategoryEnum;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    @Query("SELECT distinct p.category FROM Post p")
    List<CategoryEnum> getCategories();
}

package pl.myblog.springblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.myblog.springblog.model.Comment;

import java.util.List;


@Repository
public interface CommentRpository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByPost_id(Long id);
}

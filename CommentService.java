package sk.tuke.gamestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.TaptilesUser;

import java.util.List;

public interface GameCommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findTop5ByUserOrderByCommentedOnDesc(TaptilesUser user);
}

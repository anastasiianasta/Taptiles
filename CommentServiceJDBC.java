package sk.tuke.gamestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.entity.TaptilesUser;

import java.util.List;

public interface GameScoreRepository extends JpaRepository<Score, Integer> {
    List<Score> findTop5ByUserOrderByPlayedOnDesc(TaptilesUser user);
}

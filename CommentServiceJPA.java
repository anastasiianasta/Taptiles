package sk.tuke.gamestudio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.tuke.gamestudio.entity.TaptilesUser;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<TaptilesUser, Long> {
    Optional<TaptilesUser> findByUsername(String username);

    Optional<TaptilesUser> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    List<TaptilesUser> findTop10ByGamesPlayedGreaterThanOrderByTotalScoreDescBestScoreDescGamesPlayedDesc(int gamesPlayed);
}

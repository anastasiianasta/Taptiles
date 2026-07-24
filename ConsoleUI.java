package sk.tuke.gamestudio.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Index;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "taptiles_users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_taptiles_users_username", columnNames = "username"),
                @UniqueConstraint(name = "uk_taptiles_users_email", columnNames = "email")
        },
        indexes = {
                @Index(name = "idx_taptiles_users_total_score", columnList = "total_score"),
                @Index(name = "idx_taptiles_users_best_score", columnList = "best_score")
        }
)
public class TaptilesUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 120)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "total_score", nullable = false)
    private int totalScore;

    @Column(name = "games_played", nullable = false)
    private int gamesPlayed;

    @Column(name = "best_score", nullable = false)
    private int bestScore;

    @Column(name = "level_progress", nullable = false)
    private int levelProgress;

    @Column(name = "last_played_at")
    private LocalDateTime lastPlayedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserRole role = UserRole.ROLE_PLAYER;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (role == null) {
            role = UserRole.ROLE_PLAYER;
        }
    }

    public void recordCompletedGame(int points, int level) {
        int normalizedPoints = Math.max(points, 0);
        totalScore += normalizedPoints;
        gamesPlayed++;
        bestScore = Math.max(bestScore, normalizedPoints);
        levelProgress = Math.max(levelProgress, level);
        lastPlayedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getBestScore() {
        return bestScore;
    }

    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    public int getLevelProgress() {
        return levelProgress;
    }

    public void setLevelProgress(int levelProgress) {
        this.levelProgress = levelProgress;
    }

    public LocalDateTime getLastPlayedAt() {
        return lastPlayedAt;
    }

    public void setLastPlayedAt(LocalDateTime lastPlayedAt) {
        this.lastPlayedAt = lastPlayedAt;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}

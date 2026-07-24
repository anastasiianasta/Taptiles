package sk.tuke.gamestudio.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sk.tuke.gamestudio.entity.TaptilesUser;
import sk.tuke.gamestudio.repository.GameCommentRepository;
import sk.tuke.gamestudio.repository.GameScoreRepository;
import sk.tuke.gamestudio.server.security.CurrentUserService;

import java.time.format.DateTimeFormatter;

@Controller
public class ProfileController {
    private static final DateTimeFormatter PROFILE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final CurrentUserService currentUserService;
    private final GameScoreRepository scoreRepository;
    private final GameCommentRepository commentRepository;

    public ProfileController(CurrentUserService currentUserService,
                             GameScoreRepository scoreRepository,
                             GameCommentRepository commentRepository) {
        this.currentUserService = currentUserService;
        this.scoreRepository = scoreRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        TaptilesUser user = currentUserService.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated user is missing"));

        model.addAttribute("user", user);
        model.addAttribute("createdAtFormatted", PROFILE_TIME_FORMATTER.format(user.getCreatedAt()));
        model.addAttribute("lastPlayedAtFormatted",
                user.getLastPlayedAt() == null ? "NO DATA" : PROFILE_TIME_FORMATTER.format(user.getLastPlayedAt()));
        model.addAttribute("recentScores", scoreRepository.findTop5ByUserOrderByPlayedOnDesc(user));
        model.addAttribute("recentComments", commentRepository.findTop5ByUserOrderByCommentedOnDesc(user));
        return "taptiles-profile";
    }
}

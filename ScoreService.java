package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.entity.TaptilesUser;
import sk.tuke.gamestudio.game.taptiles.core.Board;
import sk.tuke.gamestudio.game.taptiles.core.Game;
import sk.tuke.gamestudio.game.taptiles.core.Position;
import sk.tuke.gamestudio.game.taptiles.core.Tile;
import sk.tuke.gamestudio.server.dto.CommentDto;
import sk.tuke.gamestudio.server.security.CurrentUserService;
import sk.tuke.gamestudio.server.service.UserService;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import javax.validation.Valid;
import java.util.Date;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TaptilesController {
    private static final String GAME_NAME = "taptiles";
    private static final int MAX_LEVEL = 5;
    private static final double GOOD_ENDING_PERCENT = 0.7;

    private Game game = new Game();
    private int guestLevelProgress = 0;
    private int currentRunScore = 0;
    private int lastCompletedLevel = 0;
    private int lastLevelScore = 0;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addCurrentUser(Model model) {
        model.addAttribute("currentUsername", currentUserService.getCurrentUsername());
        model.addAttribute("authenticatedUser", currentUserService.isAuthenticated());
    }

    @GetMapping("/taptiles")
    public String taptiles() {
        return "taptiles";
    }

    @GetMapping("/taptiles/new")
    public String newGame() {
        currentRunScore = 0;
        lastCompletedLevel = 0;
        lastLevelScore = 0;
        return "redirect:/taptiles/intro";
    }

    @GetMapping("/taptiles/intro")
    public String intro(Model model) {
        model.addAttribute("player", currentUserService.getCurrentUsername());
        return "taptiles-intro";
    }

    @GetMapping("/taptiles/levels")
    public String levels(Model model) {
        int completedLevel = getCompletedLevel();
        model.addAttribute("completedLevel", completedLevel);
        model.addAttribute("highestUnlockedLevel", getHighestUnlockedLevel());
        model.addAttribute("maxLevel", MAX_LEVEL);
        return "taptiles-levels";
    }

    @GetMapping("/taptiles/more")
    public String showMore(Model model) {
        double averageRating = ratingService.getAverageRating(GAME_NAME);
        model.addAttribute("comments", commentService.getComments(GAME_NAME));
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("averageRatingPercent", Math.min(100, Math.max(0, averageRating * 20)));
        if (!model.containsAttribute("commentDto")) {
            model.addAttribute("commentDto", new CommentDto());
        }
        return "taptiles-more";
    }

    @PostMapping("/taptiles/comment")
    public String addComment(@Valid @ModelAttribute("commentDto") CommentDto commentDto,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            return showMore(model);
        }

        TaptilesUser user = currentUserService.getCurrentUser()
                .orElseThrow(() -> new IllegalStateException("Authenticated user is missing"));

        commentService.addComment(new Comment(user.getUsername(), GAME_NAME, commentDto.getComment(), new Date(), user));
        ratingService.setRating(new Rating(user.getUsername(), GAME_NAME, commentDto.getRating(), new Date()));

        return "redirect:/taptiles/more";
    }

    @GetMapping("/taptiles/rating")
    public String showRating(Model model) {
        model.addAttribute("leaderboard", userService.getLeaderboard());
        return "taptiles-rating";
    }

    @GetMapping("/taptiles/transition")
    public String transition(Model model) {
        if (lastCompletedLevel <= 0 || lastCompletedLevel >= MAX_LEVEL) {
            return "redirect:/taptiles/levels";
        }

        model.addAttribute("completedLevel", lastCompletedLevel);
        model.addAttribute("nextLevel", lastCompletedLevel + 1);
        model.addAttribute("lastLevelScore", lastLevelScore);
        model.addAttribute("currentRunScore", currentRunScore);
        return "taptiles-transition";
    }

    @GetMapping("/taptiles/ending")
    public String ending(Model model) {
       // if (getCompletedLevel() < MAX_LEVEL) {
       //     return "redirect:/taptiles/levels";
        //}

        //int endingScore = getEndingScore();
        //int requiredScore = getGoodEndingScore();

        int endingScore = 1;
        int requiredScore = getGoodEndingScore();;

        model.addAttribute("player", currentUserService.getCurrentUsername());
        model.addAttribute("endingScore", endingScore);
        model.addAttribute("requiredScore", requiredScore);

        if (endingScore >= requiredScore) {
            return "taptiles-good-ending";
        }
        return "taptiles-bad-ending";
    }

    @GetMapping("/taptiles/play")
    public String play(
            @RequestParam(required = false) Integer level,
            @RequestParam(required = false) Integer row,
            @RequestParam(required = false) Integer col,
            Model model,
            RedirectAttributes redirectAttributes) {

        if (level == null && game.getBoard() == null) {
            return "redirect:/taptiles/levels";
        }

        // Do not allow starting locked levels
        if (level != null && !isLevelUnlocked(level)) {
            redirectAttributes.addFlashAttribute("levelWarning", "LEVEL LOCKED. COMPLETE PREVIOUS SIMULATION FIRST.");
            return "redirect:/taptiles/levels";
        }

        if (level != null && isLevelCompleted(level)) {
            if (getCompletedLevel() >= MAX_LEVEL) {
                return "redirect:/taptiles/ending";
            }
            redirectAttributes.addFlashAttribute("levelWarning", "COMPLETED LEVELS CANNOT BE REPLAYED.");
            return "redirect:/taptiles/levels";
        }

        if (level != null && isValidLevel(level) && (game.getBoard() == null || game.getLevel() != level)) {
            game.startNewGame(level);
        }

        if (row != null && col != null && game.getBoard() != null) {
            game.select(row, col);

            if (game.isWin()) {
                handleCompletedGame(redirectAttributes);
                if (game.getLevel() >= MAX_LEVEL) {
                    return "redirect:/taptiles/ending";
                }
                return "redirect:/taptiles/transition";
            }
        }

        model.addAttribute("game", game);
        model.addAttribute("htmlField", getHtmlField());

        return "taptiles-level";
    }

    @GetMapping("/taptiles/undo")
    public String undo() {
        if (game.getBoard() == null) {
            return "redirect:/taptiles/levels";
        }
        game.undo();
        return "redirect:/taptiles/play?level=" + game.getLevel();
    }

    public String getHtmlField() {
        if (game.getBoard() == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Board board = game.getBoard();
        Position selected = game.getSelectedTile();

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                Tile tile = board.getTile(i, j);
                if (tile != null) {
                    String selectedClass = (selected != null && selected.getRow() == i && selected.getCol() == j) ? "selected" : "";
                    // Highlight tile if it can be connected.
                    String possibleClass = game.isPossibleMove(i, j) ? "possible-move" : "";
                    sb.append(String.format(
                            "<a href='/taptiles/play?row=%d&col=%d&level=%d' class='tile %s %s'>%s</a>",
                            i, j, game.getLevel(), selectedClass, possibleClass, tile.getSymbol()
                    ));
                } else {
                    // Highlight empty cells near selected tile.
                    String pathClass = game.isPathHint(i, j) ? "path-hint" : "";
                    sb.append(String.format("<div class='tile-empty %s'></div>", pathClass));
                }
            }
        }
        return sb.toString();
    }

    private void handleCompletedGame(RedirectAttributes redirectAttributes) {
        int completedLevel = game.getLevel();
        int score = game.getScore();

        // Remember score for transition and ending screens.
        lastCompletedLevel = completedLevel;
        lastLevelScore = score;
        currentRunScore += score;

        if (currentUserService.isAuthenticated()) {
            TaptilesUser user = currentUserService.getCurrentUser()
                    .orElseThrow(() -> new IllegalStateException("Authenticated user is missing"));
            scoreService.addScore(new Score(GAME_NAME, user.getUsername(), score, new Date(), user));
            userService.recordGameResult(user.getUsername(), score, completedLevel);
            redirectAttributes.addFlashAttribute("scoreNotice", "RESULT SAVED.");
            return;
        }

        // Guest progress lives only in the current session.
        guestLevelProgress = Math.max(guestLevelProgress, completedLevel);
        redirectAttributes.addFlashAttribute("guestScoreNotice",
                "GUEST RESULT NOT SAVED. REGISTER TO STORE SCORE AND PROGRESS.");
    }

    private int getCompletedLevel() {
        return currentUserService.getCurrentUser()
                .map(TaptilesUser::getLevelProgress)
                .orElse(guestLevelProgress);
    }

    private int getHighestUnlockedLevel() {
        // Open next level after winning current level.
        return Math.min(getCompletedLevel() + 1, MAX_LEVEL);
    }

    private boolean isLevelUnlocked(Integer level) {
        return level != null && isValidLevel(level) && level <= getHighestUnlockedLevel();
    }

    private boolean isLevelCompleted(Integer level) {
        return level != null && level <= getCompletedLevel();
    }

    private boolean isValidLevel(Integer level) {
        return level != null && level >= 1 && level <= MAX_LEVEL;
    }

    private int getEndingScore() {
        return currentUserService.getCurrentUser()
                .map(TaptilesUser::getTotalScore)
                .orElse(currentRunScore);
    }

    private int getGoodEndingScore() {
        return (int) Math.ceil(getMaxGameScore() * GOOD_ENDING_PERCENT);
    }

    private int getMaxGameScore() {
        int total = 0;
        for (int level = 1; level <= MAX_LEVEL; level++) {
            total += getMaxScoreForLevel(level);
        }
        return total;
    }

    private int getMaxScoreForLevel(int level) {
        int rows = Board.getRowsForLevel(level);
        int cols = Board.getColsForLevel(level);
        return (rows * cols / 2) * Game.POINTS_FOR_PAIR;
    }
}

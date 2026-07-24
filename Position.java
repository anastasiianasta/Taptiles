package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.taptiles.core.Board;
import sk.tuke.gamestudio.game.taptiles.core.Game;
import sk.tuke.gamestudio.game.taptiles.core.Undo;
import sk.tuke.gamestudio.game.taptiles.logic.PathFinder;
import sk.tuke.gamestudio.game.taptiles.ui.ConsoleUI;
import sk.tuke.gamestudio.game.taptiles.ui.Menu;
import sk.tuke.gamestudio.service.*;

import java.util.ArrayList;

@ComponentScan(excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))
@SpringBootApplication
public class SpringClient {

    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public CommandLineRunner runner(ConsoleUI ui, Game game) {
        return args -> ui.play(game);
    }

    @Bean
    public ConsoleUI consoleUI() {
        return new ConsoleUI();
    }

    @Bean
    public Menu menu() {
        return new Menu();
    }

    @Bean
    public Game game() {
        return new Game(new Board(), 1, null, new PathFinder(), new Undo(new ArrayList<>()), 0);
    }

    @Bean
    public ScoreService scoreService() {
        //return new ScoreServiceJPA();
        return new ScoreServiceRestClient();
    }

    @Bean
    public CommentService commentService() {
        //return new CommentServiceJPA();
        return new CommentServiceRestClient();
    }

    @Bean
    public RatingService ratingService() {
        //return new RatingServiceJPA();
        return new RatingServiceRestClient();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
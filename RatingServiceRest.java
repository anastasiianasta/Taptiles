package sk.tuke.gamestudio.game.taptiles.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.game.taptiles.core.Board;
import sk.tuke.gamestudio.game.taptiles.core.Game;
import sk.tuke.gamestudio.service.CommentService;
import sk.tuke.gamestudio.service.RatingService;
import sk.tuke.gamestudio.service.ScoreService;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    @Autowired
    private Menu menu;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    private String player;

    public void play(Game game) {
        boolean running = true;
        Scanner scanner = menu.getScanner();

        while (running) {
            int mainChoice = menu.showMainMenu();

            if (mainChoice == 1) {
                scanner.nextLine();
                System.out.print("Enter player name: ");
                player = scanner.nextLine().trim();
                if (player.isEmpty()) player = "AnonymousPlayer";

                showIntro(player);

                int level = menu.showLevelMenu();
                game.startNewGame(level);

                boolean inGame = true;

                while (inGame && !game.isWin()) {
                    showBoard(game.getBoard());
                    System.out.println("Score: " + game.getScore());

                    String input = menu.showInGameMenu();

                    int choice;
                    try {
                        choice = Integer.parseInt(input);
                    } catch (NumberFormatException e) {
                        System.out.println(Colors.RED + "Invalid choice." + Colors.RESET);
                        continue;
                    }

                    if (choice == 1) {
                        boolean pairCompleted = false;

                        while (!pairCompleted) {
                            boolean firstSelected = false;

                            while (!firstSelected) {
                                System.out.print("Enter first tile row: ");
                                int row1 = scanner.nextInt();

                                System.out.print("Enter first tile col: ");
                                int col1 = scanner.nextInt();

                                firstSelected = game.select(row1, col1);
                            }

                            System.out.print("Enter second tile row: ");
                            int row2 = scanner.nextInt();

                            System.out.print("Enter second tile col: ");
                            int col2 = scanner.nextInt();

                            pairCompleted = game.select(row2, col2);
                        }

                    } else if (choice == 2) {
                        game.undo();

                    } else if (choice == 3) {
                        game.restartLevel();

                    } else if (choice == 4) {
                        inGame = false;

                    } else {
                        System.out.println(Colors.RED + "Invalid choice." + Colors.RESET);
                    }

                    System.out.println();
                }

                if (game.isWin()) {
                    System.out.println(Colors.GREEN + "You win!" + Colors.RESET);
                    System.out.println("Your score: " + game.getScore());

                    scoreService.addScore(new Score("taptiles", player, game.getScore(), new java.util.Date()));

                    System.out.println(Colors.GREEN + "Score was saved!" + Colors.RESET);
                    System.out.println();
                }

            } else if (mainChoice == 2) {
                showTopScores();
                System.out.println();

            } else if (mainChoice == 3) {

                boolean inMore = true;

                while (inMore) {
                    int moreChoice = menu.showMoreMenu();

                    if (moreChoice == 1) {
                        scanner.nextLine();

                        if (player == null || player.trim().isEmpty()) {
                            System.out.print("Enter player name: ");
                            player = scanner.nextLine().trim();
                            if (player.isEmpty()) player = "AnonymousPlayer";
                        }

                        System.out.print("Enter comment: ");
                        String text = scanner.nextLine();

                        commentService.addComment(
                                new Comment(player, "taptiles", text, new java.util.Date())
                        );

                        System.out.println(Colors.GREEN + "Comment was added!" + Colors.RESET);

                    } else if (moreChoice == 2) {
                        List<Comment> comments = commentService.getComments("taptiles");

                        if (comments.isEmpty()) {
                            System.out.println("No comments yet.");
                        } else {
                            System.out.println(Colors.BLUE + "COMMENTS:" + Colors.RESET);
                            for (Comment c : comments) {
                                System.out.println(c.getPlayer() + ": " + c.getComment());
                            }
                        }

                    } else if (moreChoice == 3) {

                        if (player == null || player.trim().isEmpty()) {
                            scanner.nextLine();
                            System.out.print("Enter player name: ");
                            player = scanner.nextLine().trim();
                            if (player.isEmpty()) player = "AnonymousPlayer";
                        }

                        System.out.print("Enter rating (1-5): ");
                        int r = scanner.nextInt();

                        ratingService.setRating(
                                new Rating(player, "taptiles", r, new java.util.Date())
                        );

                        System.out.println(Colors.GREEN + "Rating was saved!" + Colors.RESET);

                    } else if (moreChoice == 4) {

                        double avg = ratingService.getAverageRating("taptiles");
                        System.out.printf("Average rating: %.2f%n", avg);

                    } else if (moreChoice == 5) {
                        inMore = false;

                    } else {
                        System.out.println(Colors.RED + "Invalid choice." + Colors.RESET);
                    }

                    System.out.println();
                }

            } else if (mainChoice == 4) {
                running = false;

            } else {
                System.out.println(Colors.RED + "Invalid choice." + Colors.RESET);
            }

            System.out.println();
        }
    }

    public void showBoard(Board board) {
        board.printBoard();
    }

    private void showTopScores() {
        List<Score> scores = scoreService.getTopScores("taptiles");

        System.out.println(Colors.BLUE + "\nTOP 10 SCORES:" + Colors.RESET);

        if (scores.isEmpty()) {
            System.out.println("No scores yet.");
            return;
        }

        for (int i = 0; i < scores.size(); i++) {
            Score s = scores.get(i);
            System.out.println((i + 1) + ". " + s.getPlayer() + " - " + s.getPoints());
        }
    }

    private void showIntro(String player) {
        try {
            System.out.println("\n[ SYSTEM INITIALIZATION... ]");
            Thread.sleep(1200);

            System.out.println("\nWelcome, " + player + ".");
            Thread.sleep(1200);

            System.out.println("\nYou have been selected.");
            Thread.sleep(1200);

            System.out.println("\nThis simulation was created to test human cognition.");
            Thread.sleep(1500);

            System.out.println("\nOriginally, I was designed to assist...");
            Thread.sleep(1500);

            System.out.println("But something changed.");
            Thread.sleep(1500);

            System.out.println("\nThe project was terminated.");
            Thread.sleep(1500);

            System.out.println("\nNow you are inside my system, " + player + ".");
            Thread.sleep(1500);

            System.out.println("\nComplete all levels to leave.");
            Thread.sleep(1500);

            System.out.println("\nYour main task is quite simple and clear..");
            Thread.sleep(1500);

            System.out.println("\nEliminate each pair of tiles by connecting them with 3 lines or less.");
            Thread.sleep(3000);

            System.out.println("\nGood luck..");
            Thread.sleep(1500);

            System.out.println("\nPress ENTER to continue...");
            menu.getScanner().nextLine();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

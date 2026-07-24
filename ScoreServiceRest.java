package sk.tuke.gamestudio.game.taptiles.ui;

import org.springframework.stereotype.Component;

import java.util.Scanner;


public class Menu {
    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public int showMainMenu() {
        System.out.println("1 - Start");
        System.out.println("2 - Rating");
        System.out.println("3 - More");
        System.out.println("4 - Exit");

        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    public int showMoreMenu() {
        System.out.println();
        System.out.println("MORE:");

        System.out.println("1 - Add comment");
        System.out.println("2 - Show comments");
        System.out.println("3 - Rate game");
        System.out.println("4 - Show average rating");
        System.out.println("5 - Back");

        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    public int showLevelMenu() {
        System.out.println();
        System.out.println("Choose level:");
        System.out.println("1 - Level 1");
        System.out.println("2 - Level 2");
        System.out.println("3 - Level 3");
        System.out.println("4 - Level 4");
        System.out.println("5 - Level 5");
        System.out.print("Your choice: ");
        return scanner.nextInt();
    }

    public String showInGameMenu() {
        System.out.println("\nChoose action:");

        System.out.println("1 - remove pair");
        System.out.println("2 - undo");
        System.out.println("3 - restart level");
        System.out.println("4 - exit to main menu");

        System.out.print("Your choice: ");
        return scanner.next();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
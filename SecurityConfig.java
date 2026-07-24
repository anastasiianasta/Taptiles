package sk.tuke.gamestudio.game.taptiles.core;
import sk.tuke.gamestudio.game.taptiles.ui.Colors;

import java.util.Random;

public class Board {
    private int rows;
    private int cols;
    private Tile[][] tiles;

    public Board() {

    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public Tile[][] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public void generate(int level){
        rows = getRowsForLevel(level);
        cols = getColsForLevel(level);
        tiles = new Tile[rows][cols];

        char[] symbols;
        if(level == 1 || level == 2){
            symbols = new char[]{'1', '2', '3' , '4', '5', '6', '7', '8', '9'};
        } else {
            symbols = new char[]{'1', '2', '3' , '4', '5', '6', '7', '8', '9',
                    'A',  'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                    'M',  'N', 'O', 'P', 'Q', 'R',  'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        }
        int totalTiles = rows * cols;
        char[] boardSymbols = new char[totalTiles];

        Random random = new Random();

        for(int i = 0; i < totalTiles; i += 2){
            char symbol = symbols[random.nextInt(symbols.length)];
            boardSymbols[i] = symbol;
            boardSymbols[i + 1] = symbol;
        }

        for(int i = 0; i < totalTiles; i++){
            int randomIndex = random.nextInt(totalTiles);

            char temp = boardSymbols[i];
            boardSymbols[i] = boardSymbols[randomIndex];
            boardSymbols[randomIndex] = temp;
        }

        int index = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                tiles[i][j] = new Tile(boardSymbols[index]);
                index++;
            }
        }
    }

    public void printBoard() {
        System.out.print("   ");
        for (int j = 0; j < cols; j++) {
            System.out.print(Colors.GREEN + j + " " + Colors.RESET);
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {

            System.out.print(Colors.GREEN + i + "  " + Colors.RESET);

            for (int j = 0; j < cols; j++) {

                if (tiles[i][j] == null) {
                    System.out.print(Colors.YELLOW + ". " + Colors.RESET);
                } else {
                    System.out.print(tiles[i][j].getSymbol() + " ");
                }

            }

            System.out.println();
        }
    }

    public boolean isEmpty(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(tiles[i][j] != null){
                    return false;
                }
            }
        }
        return true;
    }
    public void restore(Move move){
        if (move == null) {
            return;
        }

        Position a = move.getA();
        Position b = move.getB();

        if (a != null && move.getTileA() != null) {
            tiles[a.getRow()][a.getCol()] = move.getTileA();
        }

        if (b != null && move.getTileB() != null) {
            tiles[b.getRow()][b.getCol()] = move.getTileB();
        }
    }
    public Tile getTile(int row, int col){
        if(row >= 0 && row < rows && col >= 0 && col < cols){
            return tiles[row][col];
        }
        return null;
    }

    public void removePair(Position a, Position b){
        Tile first = getTile(a.getRow(), a.getCol());
        Tile second = getTile(b.getRow(), b.getCol());

        if (first != null && second != null && first.getSymbol() == second.getSymbol()) {
            tiles[a.getRow()][a.getCol()] = null;
            tiles[b.getRow()][b.getCol()] = null;
        }
    }

    public static int getRowsForLevel(int level) {
        if (level == 2) {
            return 6;
        }
        if (level >= 3 && level <= 5) {
            return 8;
        }
        return 4;
    }

    public static int getColsForLevel(int level) {
        if (level == 2) {
            return 6;
        }
        if (level == 3) {
            return 8;
        }
        if (level == 4) {
            return 12;
        }
        if (level == 5) {
            return 16;
        }
        return 4;
    }
}

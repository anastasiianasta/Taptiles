package sk.tuke.gamestudio.game.taptiles.core;

import sk.tuke.gamestudio.game.taptiles.logic.PathFinder;
import sk.tuke.gamestudio.game.taptiles.ui.Colors;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public static final int POINTS_FOR_PAIR = 10;

    private Board board;
    private int level;
    private Position selectedTile;
    private PathFinder pathFinder;
    private Undo undoStack;
    private int score;
    private List<Position> possibleMoves = new ArrayList<>();
    private List<Position> pathHints = new ArrayList<>();

    public Game(Board board, int level, Position selectedTile, PathFinder pathFinder, Undo undoStack,  int score) {
        this.board = board;
        this.level = level;
        this.selectedTile = selectedTile;
        this.pathFinder = pathFinder;
        this.undoStack = undoStack;
        this.score = score;
    }
    public Game() {
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Position getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(Position selectedTile) {
        this.selectedTile = selectedTile;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    public void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
    }

    public Undo getUndoStack() {
        return undoStack;
    }

    public void setUndoStack(Undo undoStack) {
        this.undoStack = undoStack;
    }

    public void startNewGame(int level){
        this.level = level;
        this.score = 0;
        this.selectedTile = null;
        clearHints();

        if (this.board == null) {
            this.board = new Board();
        }
        if (this.pathFinder == null) {
            this.pathFinder = new PathFinder();
        }
        if (this.undoStack == null) {
            this.undoStack = new Undo(new ArrayList<>());
        } else {
            this.undoStack.clear();
        }
        board.generate(level);
    }

    public boolean select(int row, int col){
        Tile currentTile = board.getTile(row, col);

        if (currentTile == null) {
            System.out.println(Colors.RED + "There is no tile at this position." + Colors.RESET);
            if (selectedTile != null) {
                selectedTile = null;
            }
            clearHints();
            return false;
        }

        Position currentPosition = new Position(row, col);

        //first tile
        if (selectedTile == null) {
            selectedTile = currentPosition;
            showAvailableMoves();
            System.out.println(Colors.BLUE + "First tile selected." + Colors.RESET);
            return true;
        }

        //same tile
        if (selectedTile.getRow() == row && selectedTile.getCol() == col) {
            System.out.println(Colors.RED + "You selected the same tile." + Colors.RESET);
            selectedTile = null;
            clearHints();
            return false;
        }

        Tile firstTile = board.getTile(selectedTile.getRow(), selectedTile.getCol());
        Tile secondTile = currentTile;

        //if can connect
        if (pathFinder.canConnect(board, selectedTile, currentPosition)) {
            Move move = new Move(new Position(selectedTile.getRow(), selectedTile.getCol()), new Position(currentPosition.getRow(), currentPosition.getCol()), firstTile, secondTile);

            board.removePair(selectedTile, currentPosition);
            undoStack.push(move);
            score += POINTS_FOR_PAIR;

            System.out.println(Colors.GREEN + "Pair removed!" + Colors.RESET);
            selectedTile = null;
            clearHints();
            return true;
        } else {
            System.out.println(Colors.RED + "These tiles cannot be connected." + Colors.RESET);
            selectedTile = null;
            clearHints();
            return false;
        }
    }

    public boolean isWin(){
        return board.isEmpty();
    }

    public void undo(){
        if (undoStack == null || undoStack.isEmpty()) {
            System.out.println(Colors.RED + "There is nothing to undo." + Colors.RESET);
            return;
        }
        Move lastMove = undoStack.pop();
        board.restore(lastMove);
        clearHints();

        if (score >= 15) {
            score -= 15;
        }
        selectedTile = null;
        System.out.println(Colors.YELLOW + "Last move restored." + Colors.RESET);
    }

    public void restartLevel(){
        if (board == null) {
            board = new Board();
        }
        board.generate(level);
        selectedTile = null;
        score = 0;
        clearHints();
        if (undoStack != null) {
            undoStack.clear();
        }
        System.out.println(Colors.YELLOW + "Level restarted." + Colors.RESET);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isPossibleMove(int row, int col) {
        return containsPosition(possibleMoves, row, col);
    }

    public boolean isPathHint(int row, int col) {
        return containsPosition(pathHints, row, col);
    }

    private void showAvailableMoves() {
        clearHints();

        if (board == null || selectedTile == null) {
            return;
        }
        if (pathFinder == null) {
            pathFinder = new PathFinder();
        }
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                Position other = new Position(row, col);
                if (pathFinder.canConnect(board, selectedTile, other)) {
                    possibleMoves.add(other);
                }
            }
        }

        addSimplePathHints();
    }

    private void addSimplePathHints() {
        addHintsInDirection(-1, 0);
        addHintsInDirection(1, 0);
        addHintsInDirection(0, -1);
        addHintsInDirection(0, 1);
    }

    private void addHintsInDirection(int rowStep, int colStep) {
        int row = selectedTile.getRow() + rowStep;
        int col = selectedTile.getCol() + colStep;

        while (row >= 0 && row < board.getRows() && col >= 0 && col < board.getCols()) {
            if (board.getTile(row, col) != null) {
                return;
            }
            pathHints.add(new Position(row, col));
            row += rowStep;
            col += colStep;
        }
    }

    private void clearHints() {
        // Remove old highlighted tiles.
        possibleMoves.clear();
        pathHints.clear();
    }

    private boolean containsPosition(List<Position> positions, int row, int col) {
        for (Position position : positions) {
            if (position.getRow() == row && position.getCol() == col) {
                return true;
            }
        }
        return false;
    }
}

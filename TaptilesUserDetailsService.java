package sk.tuke.gamestudio.game.taptiles.logic;

import sk.tuke.gamestudio.game.taptiles.core.Position;
import sk.tuke.gamestudio.game.taptiles.core.Board;
import sk.tuke.gamestudio.game.taptiles.core.Tile;

public class PathFinder {

    public boolean canConnect(Board board, Position a, Position b) {
        if (board == null || a == null || b == null) {
            return false;
        }

        if (a.getRow() == b.getRow() && a.getCol() == b.getCol()) {
            return false;
        }

        Tile tileA = board.getTile(a.getRow(), a.getCol());
        Tile tileB = board.getTile(b.getRow(), b.getCol());

        if (tileA == null || tileB == null) {
            return false;
        }

        if (tileA.getSymbol() != tileB.getSymbol()) {
            return false;
        }

        return checkLine(board, a, b) || checkOneTurn(board, a, b) || checkTwoTurns(board, a, b);
    }

    private boolean checkLine(Board board, Position a, Position b) {
        return isPathClear(board, a, b);
    }

    private boolean checkOneTurn(Board board, Position a, Position b) {
        Position corner1 = new Position(a.getRow(), b.getCol());
        Position corner2 = new Position(b.getRow(), a.getCol());

        if (isEmptyOrOutside(board, corner1, a, b)
                && isPathClear(board, a, corner1)
                && isPathClear(board, corner1, b)) {
            return true;
        }

        if (isEmptyOrOutside(board, corner2, a, b) && isPathClear(board, a, corner2) && isPathClear(board, corner2, b)) {
            return true;
        }

        return false;
    }

    private boolean checkTwoTurns(Board board, Position a, Position b) {
        for (int row = -1; row <= board.getRows(); row++) {
            Position middle1 = new Position(row, a.getCol());
            Position middle2 = new Position(row, b.getCol());

            if (isEmptyOrOutside(board, middle1, a, b)
                    && isEmptyOrOutside(board, middle2, a, b)
                    && isPathClear(board, a, middle1)
                    && isPathClear(board, middle1, middle2)
                    && isPathClear(board, middle2, b)) {
                return true;
            }
        }

        for (int col = -1; col <= board.getCols(); col++) {
            Position middle1 = new Position(a.getRow(), col);
            Position middle2 = new Position(b.getRow(), col);

            if (isEmptyOrOutside(board, middle1, a, b)
                    && isEmptyOrOutside(board, middle2, a, b)
                    && isPathClear(board, a, middle1)
                    && isPathClear(board, middle1, middle2)
                    && isPathClear(board, middle2, b)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPathClear(Board board, Position a, Position b) {
        int row1 = a.getRow();
        int col1 = a.getCol();
        int row2 = b.getRow();
        int col2 = b.getCol();

        if (row1 == row2) {
            int start = Math.min(col1, col2) + 1;
            int end = Math.max(col1, col2);

            for (int col = start; col < end; col++) {
                if (!isEmptyOrOutside(board, new Position(row1, col), a, b)) {
                    return false;
                }
            }
            return true;
        }

        if (col1 == col2) {
            int start = Math.min(row1, row2) + 1;
            int end = Math.max(row1, row2);

            for (int row = start; row < end; row++) {
                if (!isEmptyOrOutside(board, new Position(row, col1), a, b)) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }

    private boolean isEmptyOrOutside(Board board, Position pos, Position a, Position b) {
        if ((pos.getRow() == a.getRow() && pos.getCol() == a.getCol()) || (pos.getRow() == b.getRow() && pos.getCol() == b.getCol())) {
            return true;
        }

        if (pos.getRow() < 0 || pos.getRow() >= board.getRows() || pos.getCol() < 0 || pos.getCol() >= board.getCols()) {
            return true;
        }

        return board.getTile(pos.getRow(), pos.getCol()) == null;
    }
}
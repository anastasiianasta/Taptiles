package sk.tuke.gamestudio.game.taptiles.core;

import java.util.List;

public class Undo {
    private List<Move> moves;

    public Undo(List<Move> moves) {
        this.moves = moves;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    //
    public Move pop(){
        if(moves.isEmpty()){
            return null;
        }
        return moves.remove(moves.size()-1);
    } //undo

    public void push(Move move) {
        moves.add(move);
    } //remember move

    public boolean isEmpty(){
        return moves.isEmpty();
    }

    public void clear(){
        moves.clear();
    }
}

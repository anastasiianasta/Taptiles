package sk.tuke.gamestudio.game.taptiles.core;

public class Move {
    private Position a;
    private Position b;
    private Tile tileA;
    private Tile tileB;

    public Move(Position a, Position b, Tile tileA, Tile tileB) {
        this.a = a;
        this.b = b;
        this.tileA = tileA;
        this.tileB = tileB;
    }

    public Position getA() {
        return a;
    }

    public void setA(Position a) {
        this.a = a;
    }

    public Position getB() {
        return b;
    }

    public void setB(Position b) {
        this.b = b;
    }

    public Tile getTileA() {
        return tileA;
    }

    public void setTileA(Tile tileA) {
        this.tileA = tileA;
    }

    public Tile getTileB() {
        return tileB;
    }

    public void setTileB(Tile tileB) {
        this.tileB = tileB;
    }
}

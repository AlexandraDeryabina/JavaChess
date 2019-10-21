package ru.lanit.chess.piece;

import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;

public abstract class Chessman {
    Coordinates[] route;

    private String name;

    private Coordinates coordinates;

    private Color color;

    private boolean firstMove = true;

    private boolean alive = true;

    Chessman(Coordinates coordinates, Color color) {
        this.coordinates = coordinates;
        this.color = color;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public abstract Coordinates generateMoveCoordinates();

    public abstract Character getIcon();

    public Coordinates[] getRoute() {
        return route;
    }

    boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isMoveValid(Coordinates targetCoordinates, boolean isCellEmpty) {
        return true;
    }

    public String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}

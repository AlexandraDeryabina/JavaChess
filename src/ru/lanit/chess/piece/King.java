package ru.lanit.chess.piece;

import ru.lanit.chess.utility.ChessRandom;
import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;

public class King extends Chessman {
    public King(Coordinates coordinates, Color color) {
        super(coordinates, color);
        setName("King");
    }

    @Override
    public Coordinates generateMoveCoordinates() {
        int randomStep = new ChessRandom().generate(1, 8);
        // У короля не может быть фигур, через которые перешагнул
        route = new Coordinates[0];

        int x = getCoordinates().getX();
        int y = getCoordinates().getY();

        switch (randomStep) {
            case 1: return new Coordinates(x + 1, y);
            case 2: return new Coordinates(x - 1, y);
            case 3: return new Coordinates(x, y + 1);
            case 4: return new Coordinates(x, y - 1);
            case 5: return new Coordinates(x + 1, y + 1);
            case 6: return new Coordinates(x - 1, y - 1);
            case 7: return new Coordinates(x + 1, y - 1);
            default: return new Coordinates(x - 1, y + 1);
        }
    }

    @Override
    public Character getIcon() {
        return Color.WHITE == getColor() ? '♕' : '♛';
    }
}

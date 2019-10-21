package ru.lanit.chess.piece;

import ru.lanit.chess.utility.ChessRandom;
import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;

public class Horse extends Chessman {
    public Horse(Coordinates coordinates, Color color) {
        super(coordinates, color);
        setName("Horse");
    }

    @Override
    public Coordinates generateMoveCoordinates() {
        int randomStep = new ChessRandom().generate(1, 8);
        // Конь может перешагивать через фигуры, поэтому маршрут хода можно оставить пустым
        route = new Coordinates[0];

        int x = getCoordinates().getX();
        int y = getCoordinates().getY();
        switch (randomStep) {
            case 1: return new Coordinates(x + 1, y - 2);
            case 2: return new Coordinates(x + 2, y - 1);
            case 3: return new Coordinates(x + 1, y + 2);
            case 4: return new Coordinates(x + 2, y + 1);
            case 5: return new Coordinates(x - 1, y + 2);
            case 6: return new Coordinates(x - 2, y + 1);
            case 7: return new Coordinates(x - 1, y - 2);
            default: return new Coordinates(x - 2, y - 1);
        }
    }

    @Override
    public Character getIcon() {
        return Color.WHITE == getColor() ? '♘' : '♞';
    }
}

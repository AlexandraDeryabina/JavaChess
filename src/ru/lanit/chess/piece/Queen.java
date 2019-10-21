package ru.lanit.chess.piece;

import ru.lanit.chess.utility.ChessRandom;
import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;

public class Queen extends Chessman {
    public Queen(Coordinates coordinates, Color color) {
        super(coordinates, color);
        setName("Queen");
    }

    @Override
    public Coordinates generateMoveCoordinates() {
        /*
         * Генерируем вид хода - как слон или как ладья
         * 0 - слон
         * 1 - ладья
         */
        int randomType = new ChessRandom().generate(0, 1);

        if (0 == randomType) {
            Chessman chessman = new Elephant(getCoordinates(), getColor());
            Coordinates coordinates = chessman.generateMoveCoordinates();
            route = chessman.getRoute();
            return coordinates;
        } else {
            Chessman chessman = new Rook(getCoordinates(), getColor());
            Coordinates coordinates = chessman.generateMoveCoordinates();
            route = chessman.getRoute();
            return coordinates;
        }
    }

    @Override
    public Character getIcon() {
        return Color.WHITE == getColor() ? '♔' : '♚';
    }
}

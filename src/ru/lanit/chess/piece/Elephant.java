package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.utility.ChessRandom;
import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;

public class Elephant extends Chessman {
    public Elephant(Coordinates coordinates, Color color) {
        super(coordinates, color);
        setName("Elephant");
    }

    @Override
    public Coordinates generateMoveCoordinates() {
        int randomStep = 0;
        while (0 == randomStep) {
            randomStep = new ChessRandom().generate(-1 * (Board.BOARD_SIZE_ONE_SIDE - 1), (Board.BOARD_SIZE_ONE_SIDE - 1));
        }

        // Запоминаем маршрут хода, чтобы потом проверить, были ли на пути фигуры
        route = new Coordinates[Math.abs(randomStep) - 1];

        int x = getCoordinates().getX();
        int y = getCoordinates().getY();
        if (randomStep > 0) {
            for (int i = 1; i < randomStep; i++) {
                route[i - 1] = new Coordinates(x + i, y + i);
            }
        }
        if (randomStep < 0) {
            for (int i = 1; i < Math.abs(randomStep); i++) {
                route[i - 1] = new Coordinates(x - i, y - i);
            }
        }

        return new Coordinates(x + randomStep, y + randomStep);
    }

    @Override
    public Character getIcon() {
        return Color.WHITE == getColor() ? '♗' : '♝';
    }
}

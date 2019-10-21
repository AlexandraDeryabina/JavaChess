package ru.lanit.chess.piece;

import ru.lanit.chess.game.Board;
import ru.lanit.chess.utility.ChessRandom;
import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;

public class Rook extends Chessman {
    public Rook(Coordinates coordinates, Color color) {
        super(coordinates, color);
        setName("Rook");
    }

    @Override
    public Coordinates generateMoveCoordinates() {
        int randomStep = 0;

        while (0 == randomStep) {
            randomStep = new ChessRandom().generate(-1 * (Board.BOARD_SIZE_ONE_SIDE - 1), (Board.BOARD_SIZE_ONE_SIDE - 1));
        }

        // Запоминаем маршрут хода, чтобы потом проверить, были ли на пути фигуры
        route = new Coordinates[Math.abs(randomStep) - 1];

        /*
         * Генерируем направление
         * 0 - горизонталь (координата x меняется, y остается прежней)
         * 1 - вертикаль (координата y меняется, x остается прежней)
         */
        int randomForDirection = new ChessRandom().generate(0, 1);

        int x = getCoordinates().getX();
        int y = getCoordinates().getY();

        if (0 == randomForDirection) {
            if (randomStep > 0) {
                for (int i = 1; i < randomStep; i++) {
                    route[i - 1] = new Coordinates(x + i, y);
                }
            }
            if (randomStep < 0) {
                for (int i = 1; i < Math.abs(randomStep); i++) {
                    route[i - 1] = new Coordinates(x - i, y);
                }
            }
            return new Coordinates(x + randomStep, y);
        } else {
            if (randomStep > 0) {
                for (int i = 1; i < randomStep; i++) {
                    route[i - 1] = new Coordinates(x, y + i);
                }
            }
            if (randomStep < 0) {
                for (int i = 1; i < Math.abs(randomStep); i++) {
                    route[i - 1] = new Coordinates(x, y - i);
                }
            }
            return new Coordinates(x, y + randomStep);
        }
    }

    @Override
    public Character getIcon() {
        return Color.WHITE == getColor() ? '♖' : '♜';
    }
}

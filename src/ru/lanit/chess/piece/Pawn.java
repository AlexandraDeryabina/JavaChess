package ru.lanit.chess.piece;

import ru.lanit.chess.utility.ChessRandom;
import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;

public class Pawn extends Chessman {
    public Pawn(Coordinates coordinates, Color color) {
        super(coordinates, color);
        setName("Pawn");
    }

    @Override
    public Coordinates generateMoveCoordinates() {
        // 2 - при первом ходе идем на 2 клетки вперед
        int randomStep = new ChessRandom().generate(1, 2);
        // 1 - пробуем срубить (наискосок)
        int randomEat = new ChessRandom().generate(1, 2);

        int x = getCoordinates().getX();
        int y = getCoordinates().getY();

        if (isFirstMove() && 2 == randomStep) {
            if (Color.WHITE == getColor()) {
                route = new Coordinates[]{new Coordinates(x, y + 1)};
                return new Coordinates(x, y + 2);
            } else {
                route = new Coordinates[]{new Coordinates(x, y - 1)};
                return new Coordinates(x, y - 2);
            }
        } else {
            route = new Coordinates[0];
            // рубить можем в двух направлениях (влево или вправо наискосок)
            if (1 == randomEat && 1 == randomStep) {
                if (Color.WHITE == getColor()) {
                    return new Coordinates(x + 1, y + 1);
                } else {
                    return new Coordinates(x - 1, y - 1);
                }
            } else if (1 == randomEat && 2 == randomStep) {
                if (Color.WHITE == getColor()) {
                    return new Coordinates(x - 1, y + 1);
                } else {
                    return new Coordinates(x - 1, y - 1);
                }
            } else {
                if (Color.WHITE == getColor()) {
                    return new Coordinates(x, y + 1);
                } else {
                    return new Coordinates(x, y - 1);
                }
            }
        }
    }

    @Override
    public boolean isMoveValid(Coordinates targetCoordinates, boolean isCellEmpty) {
        int deltaY = targetCoordinates.getY() - getCoordinates().getY();
        int deltaX = targetCoordinates.getX() - getCoordinates().getX();
        // Если клетка для хода пустая, то можно сходить только по вертикали
        if (isCellEmpty && 0 == deltaX) {
            return true;
        }
        // Если клетка для хода занята, то можно сходить по диагонали
        if (!isCellEmpty && 1 == Math.abs(deltaX)) {
            if (Color.WHITE == getColor() && 1 == deltaY) {
                return true;
            }
            if (Color.BLACK == getColor() && -1 == deltaY) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Character getIcon() {
        return Color.WHITE == getColor() ? '♙' : '♟';
    }
}

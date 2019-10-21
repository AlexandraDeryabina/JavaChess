package ru.lanit.chess.game;

import ru.lanit.chess.utility.ChessRandom;
import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;
import ru.lanit.chess.exception.*;
import ru.lanit.chess.piece.Chessman;
import ru.lanit.chess.piece.King;

public class Mover {

    private Board board;

    public Mover(Board board) {
        this.board = board;
    }

    public void move(Color color) throws GameException {
        Chessman[] current;
        if (Color.WHITE == color) {
            current = board.getWhiteChessmen();
        } else {
            current = board.getBlackChessmen();
        }

        int maxNumberOfMoves = 1000;
        for (int i = 0; i < maxNumberOfMoves; i++) {
            if (i == maxNumberOfMoves - 1) {
                throw new MaxNumberOfMovesException(color);
            }

            int randomIndex = new ChessRandom().generate(0, Board.BOARD_SIZE_ONE_SIDE * 2 - 1);
            // Рандомная фигура уже срублена
            if (!current[randomIndex].isAlive()) {
                continue;
            }

            Coordinates targetCoordinates = current[randomIndex].generateMoveCoordinates();
            // Невалидные коордидаты для хода - выходят за пределы доски
            if (board.isCoordinatesInvalid(targetCoordinates)) {
                continue;
            }

            // У фигуры есть "препятствия" на пути, ход невозможен
            if (!isChessmanRouteEmpty(current[randomIndex].getRoute())) {
                continue;
            }

            Chessman ChessmanInNotEmptyCell = board.getChessmanByCoordinates(targetCoordinates);
            // Целевая клетка занята фигурой того же цвета - ход невозможен
            if (null != ChessmanInNotEmptyCell && ChessmanInNotEmptyCell.getColor() == current[randomIndex].getColor()) {
                continue;
            }

            // Разрешен ли ход для фигур, у которых разное поведение для "ходить" и "рубить"
            if (!current[randomIndex].isMoveValid(targetCoordinates, board.isCellEmpty(targetCoordinates))) {
                continue;
            }

            if (board.isCellEmpty(targetCoordinates)) {
                moveChessman(current[randomIndex], targetCoordinates);
                break;
            } else {
                killEnemy(current[randomIndex], ChessmanInNotEmptyCell);
                moveChessman(current[randomIndex], targetCoordinates);
                break;
            }
        }
    }
    
    private boolean isChessmanRouteEmpty(Coordinates[] route) {
        for (Coordinates coordinates: route) {
            if (!board.isCellEmpty(coordinates)) {
                return false;
            }
        }
        return true;
    }

    private void moveChessman(Chessman chessman, Coordinates targetCoordinates) {
        chessman.setFirstMove(false);
        board.clearCell(new Coordinates(chessman.getCoordinates()));
        chessman.setCoordinates(targetCoordinates);
        board.putChessman(chessman);
    }

    private void killEnemy(Chessman current, Chessman enemy) throws GameException {
        if (enemy instanceof King) {
            String currentChessman = current.getColor().toString() + " " + current.getName() + " ";
            String enemyChessman = enemy.getColor().toString() + " " + enemy.getName() + " ";
            String message = "The " + currentChessman + "will eat the " + enemyChessman
                    + ". Coordinates of " + currentChessman + current.getCoordinates().toString()
                    + ". Coordinates of " + enemyChessman + enemy.getCoordinates().toString();
            throw new KingIsDiedException(enemy.getColor(), message);
        }
        enemy.setAlive(false);
    }
}

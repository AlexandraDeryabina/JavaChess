package ru.lanit.chess.game;

import ru.lanit.chess.utility.Color;
import ru.lanit.chess.utility.Coordinates;
import ru.lanit.chess.piece.*;

public class Board {
    public static int BOARD_SIZE_ONE_SIDE = 8;

    private Chessman[][] board;

    private Chessman[] whiteChessmen;

    private Chessman[] blackChessmen;

    public Board() {
        this.board = new Chessman[BOARD_SIZE_ONE_SIDE][BOARD_SIZE_ONE_SIDE];
        this.whiteChessmen = new Chessman[BOARD_SIZE_ONE_SIDE * 2];
        this.blackChessmen = new Chessman[BOARD_SIZE_ONE_SIDE * 2];
    }

    public void init() {
        initChessmen();
        initChessmenOnBoard();
    }

    private void initChessmen() {
        for (int i = 0; i < BOARD_SIZE_ONE_SIDE; i++) {
            whiteChessmen[i] = new Pawn(new Coordinates(i, 1), Color.WHITE);
            blackChessmen[i] = new Pawn(new Coordinates(i, BOARD_SIZE_ONE_SIDE - 2), Color.BLACK);
        }

        whiteChessmen[BOARD_SIZE_ONE_SIDE] = new Rook(new Coordinates(0, 0), Color.WHITE);
        whiteChessmen[BOARD_SIZE_ONE_SIDE + 1] = new Horse(new Coordinates(1, 0), Color.WHITE);
        whiteChessmen[BOARD_SIZE_ONE_SIDE + 2] = new Elephant(new Coordinates(2, 0), Color.WHITE);
        whiteChessmen[BOARD_SIZE_ONE_SIDE + 3] = new Queen(new Coordinates(3, 0), Color.WHITE);
        whiteChessmen[BOARD_SIZE_ONE_SIDE + 4] = new King(new Coordinates(4, 0), Color.WHITE);
        whiteChessmen[BOARD_SIZE_ONE_SIDE + 5] = new Elephant(new Coordinates(5, 0), Color.WHITE);
        whiteChessmen[BOARD_SIZE_ONE_SIDE + 6] = new Horse(new Coordinates(6, 0), Color.WHITE);
        whiteChessmen[BOARD_SIZE_ONE_SIDE + 7] = new Rook(new Coordinates(7, 0), Color.WHITE);

        blackChessmen[BOARD_SIZE_ONE_SIDE ] = new Rook(new Coordinates(0, BOARD_SIZE_ONE_SIDE - 1), Color.BLACK);
        blackChessmen[BOARD_SIZE_ONE_SIDE + 1] = new Horse(new Coordinates(1, BOARD_SIZE_ONE_SIDE - 1), Color.BLACK);
        blackChessmen[BOARD_SIZE_ONE_SIDE + 2] = new Elephant(new Coordinates(2, BOARD_SIZE_ONE_SIDE - 1), Color.BLACK);
        blackChessmen[BOARD_SIZE_ONE_SIDE + 3] = new Queen(new Coordinates(3, BOARD_SIZE_ONE_SIDE - 1), Color.BLACK);
        blackChessmen[BOARD_SIZE_ONE_SIDE + 4] = new King(new Coordinates(4, BOARD_SIZE_ONE_SIDE - 1), Color.BLACK);
        blackChessmen[BOARD_SIZE_ONE_SIDE + 5] = new Elephant(new Coordinates(5, BOARD_SIZE_ONE_SIDE - 1), Color.BLACK);
        blackChessmen[BOARD_SIZE_ONE_SIDE + 6] = new Horse(new Coordinates(6, BOARD_SIZE_ONE_SIDE - 1), Color.BLACK);
        blackChessmen[BOARD_SIZE_ONE_SIDE + 7] = new Rook(new Coordinates(7, BOARD_SIZE_ONE_SIDE - 1), Color.BLACK);
    }

    private void initChessmenOnBoard() {
        for (Chessman chessman: whiteChessmen) {
            board[chessman.getCoordinates().getX()][chessman.getCoordinates().getY()] = chessman;
        }

        for (Chessman chessman: blackChessmen) {
            board[chessman.getCoordinates().getX()][chessman.getCoordinates().getY()] = chessman;
        }
    }

    boolean isCellEmpty(Coordinates coordinates) {
        if (isCoordinatesInvalid(coordinates)) {
            return true;
        }
        return null == board[coordinates.getX()][coordinates.getY()];
    }

    void clearCell(Coordinates coordinates) {
        if (isCoordinatesInvalid(coordinates)) {
            return;
        }
        board[coordinates.getX()][coordinates.getY()] = null;
    }

    void putChessman(Chessman chessman) {
        if (null == chessman) {
            return;
        }
        board[chessman.getCoordinates().getX()][chessman.getCoordinates().getY()] = chessman;
    }

    public Chessman[][] getBoard() {
        return board;
    }

    Chessman[] getWhiteChessmen() {
        return whiteChessmen;
    }

    Chessman[] getBlackChessmen() {
        return blackChessmen;
    }

    Chessman getChessmanByCoordinates(Coordinates coordinates) {
        if (isCoordinatesInvalid(coordinates)) {
            return null;
        }
        return board[coordinates.getX()][coordinates.getY()];
    }

    boolean isCoordinatesInvalid(Coordinates coordinates) {
        if (null == coordinates) {
            return true;
        }
        if (coordinates.getX() >= 0 && coordinates.getX() < BOARD_SIZE_ONE_SIDE
                && coordinates.getY() >= 0 && coordinates.getY() < BOARD_SIZE_ONE_SIDE) {
            return false;
        }
        return true;
    }

    public void print() {
        System.out.print("\n");
        for (int i = 0; i < BOARD_SIZE_ONE_SIDE; i++) {
            for (int j = 0; j < BOARD_SIZE_ONE_SIDE; j++) {
                Coordinates coordinates = new Coordinates(j, i);
                if (isCellEmpty(coordinates)) {
                    System.out.print('*');
                } else {
                    System.out.print(getChessmanByCoordinates(coordinates).getIcon());
                }
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}

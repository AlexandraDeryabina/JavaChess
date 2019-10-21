package ru.lanit.chess.exception;

public class GameOverException extends Exception {
    private String message;

    public GameOverException() {
        message = "Game over";
    }

    public GameOverException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}

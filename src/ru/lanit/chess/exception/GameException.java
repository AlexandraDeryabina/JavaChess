package ru.lanit.chess.exception;

import ru.lanit.chess.utility.Color;

abstract public class GameException extends Exception {
    Color failColor;

    GameException(Color failColor) {
        this.failColor = failColor;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}

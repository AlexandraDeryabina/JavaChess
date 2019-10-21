package ru.lanit.chess.exception;

import ru.lanit.chess.utility.Color;

public class MaxNumberOfMovesException extends GameException {
    public MaxNumberOfMovesException(Color failColor) {
        super(failColor);
    }

    @Override
    public String getMessage() {
        return "Game over: " + failColor.toString() + ". Have no variants to move.";
    }
}

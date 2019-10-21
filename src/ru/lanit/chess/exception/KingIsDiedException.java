package ru.lanit.chess.exception;

import ru.lanit.chess.utility.Color;

public class KingIsDiedException extends GameException {
    private String message;

    public KingIsDiedException(Color failColor, String message) {
        super(failColor);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "Game over: " + failColor.toString() + ". It's a checkmate, king died." + message;
    }
}

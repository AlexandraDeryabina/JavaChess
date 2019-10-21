import ru.lanit.chess.game.Board;
import ru.lanit.chess.game.Mover;
import ru.lanit.chess.utility.Color;
import ru.lanit.chess.exception.GameException;
import ru.lanit.chess.exception.GameOverException;

public class Game {
    public static void main(String[] args) {
        Board board = new Board();
        board.init();
        System.out.println("Chess board before: \n");
        board.print();
        Mover mover = new Mover(board);
        try {
            while (true) {
                for (Color color : Color.values()) {
                    try {
                        mover.move(color);
                    } catch (GameException e) {
                        throw new GameOverException(e.getMessage());
                    }
                }
            }
        } catch (GameOverException e) {
            System.out.println(e.getMessage());
            System.out.println("Chess board after: \n");
            board.print();
        }
    }
}

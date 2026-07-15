package strategy;

import models.Board;
import models.Move;
import models.Player;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
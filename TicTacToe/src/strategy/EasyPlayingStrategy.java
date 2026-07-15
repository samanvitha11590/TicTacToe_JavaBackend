package strategy;

//import models.BotPlayingStrategy; //If I put this it gives error when i override

import models.Board;
import models.Cell;
import models.Move;
import models.enums.CellState;

public class EasyPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move makeMove(Board board) {
       for(int row=0; row<board.getSize(); row++){
           for(int col=0; col<board.getSize(); col++){
            if(board.getCells().get(row).get(col).getCellState().equals(CellState.EMPTY)){
                return new Move(null, new Cell(row,col));  // we don't know player here. so just pass null and set player in the player class.
            }
           }
       }
       return null;
    }
}
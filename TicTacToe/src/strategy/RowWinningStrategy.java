package strategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{

    private HashMap<String, Integer> rowMaps[];
    public RowWinningStrategy() {
        this.rowMaps = new HashMap[10000];
        //we have to initialise a hashmap at every row. just like a 2d arraylist
        for(int i=0; i<10000; i++){
            rowMaps[i]=new HashMap<>();
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move){
    // step 1: get current player
        Player currPlayer = move.getPlayer();
        int currRow = move.getCell().getRow();
        HashMap<String, Integer> currRowMap = rowMaps[currRow];
        //if we make move at row 0, we get the hashmap at row 0.

        String currSymbol = move.getPlayer().getSymbol().getName();
        if(!currRowMap.containsKey(currSymbol)){
            currRowMap.put(currSymbol, 0);
        }
        currRowMap.put(currSymbol, currRowMap.get(currSymbol)+1);
        return currRowMap.get(currSymbol)== board.getSize();
    }
}

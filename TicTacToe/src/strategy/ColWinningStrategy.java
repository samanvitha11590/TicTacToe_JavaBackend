package strategy;

import models.Board;
import models.Move;
import models.Player;

import java.util.HashMap;

public class ColWinningStrategy implements WinningStrategy{

    private HashMap<String, Integer> colMaps[];
    public ColWinningStrategy() {
        this.colMaps = new HashMap[10000];
        //we have to initialise a hashmap at every col. just like a 2d arraylist
        for(int i=0; i<10000; i++){
            colMaps[i]=new HashMap<>();
        }
    }
    @Override
    public boolean checkWinner(Board board, Move move){
        // step 1: get current player
        Player currPlayer = move.getPlayer();
        int currCol = move.getCell().getCol();
        HashMap<String, Integer> currColMap = colMaps[currCol];
        //if we make move at col 0, we get the hashmap at col 0.

        String currSymbol = move.getPlayer().getSymbol().getName();
        if(!currColMap.containsKey(currSymbol)){
            currColMap.put(currSymbol, 0);
        }
        currColMap.put(currSymbol, currColMap.get(currSymbol)+1);
        return currColMap.get(currSymbol)== board.getSize();
    }
}

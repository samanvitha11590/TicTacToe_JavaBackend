package models;

import exceptions.InvalidPlayerCountException;
import models.enums.CellState;
import models.enums.GameState;
import strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextTurn;
    private List<WinningStrategy> winningStrategies;

    public Game(int size,
                 List<Player> players,
                 List<WinningStrategy> winningStrategies) {
        this.board = new Board(size);  // strong HAS-A, composition
        this.players = players; // WEAK HAS-A
        this.winningStrategies = winningStrategies;
        this.nextTurn = 0;
        this.moves = new ArrayList<>();
        this.gameState = GameState.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextTurn() {
        return nextTurn;
    }

    public void setNextTurn(int nextTurn) {
        this.nextTurn = nextTurn;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public void makeMove(Game game){
        Player player = players.get(nextTurn);
        System.out.println(" It's "+ player.getName()+ "'s turn");
        Move move = player.makeMove(this);

        nextTurn = (nextTurn+1)%players.size(); //circular movement within the shuffled array

        //fill the cell
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Cell currCell = board.getCells().get(row).get(col);
        currCell.setCellState(CellState.FILLED);
        currCell.setPlayer(player);

        //add this move to the List<Move>
        this.moves.add(move);

        //check winner (at every move we check the winner)
        if(checkWinner(move)){
            this.setWinner(player);
            this.setGameState(GameState.COMPLETED);
        }
           //if game ends but we don't have winner. ie, all cells are filled but there is no winner
        else if(moves.size()==this.board.getSize()*this.board.getSize()){
            this.setGameState(GameState.DRAW);
        }
    }

      private boolean checkWinner(Move move) {
          //we have to call factory method here. We have to iterate on the list of winningStrategies that we defined for our game, and check winner using all startegies, eg:row/column/diagonal
          //eg: we decided to use rowWinningStrategy and Col winningStrategy. ie, after every move we have to check the row and col if there is any winner
          for(WinningStrategy winningStrategy: winningStrategies){
             if(winningStrategy.checkWinner(this.board, move)){
                return true;
             }
          }
       return false;
      }

    public static Builder getBuilder(){
        return new Builder();
    }

      public static class Builder{
        int size;
        List<Player> players;
        List<WinningStrategy> winningStrategies;

          public int getSize() {return size;}
          public Builder setSize(int size) {
              this.size = size;
              return this;
          }

          public List<Player> getPlayers() {return players;}
          public Builder setPlayers(List<Player> players) {
              this.players = players;
              return this;
          }

          public List<WinningStrategy> getWinningStrategies() {return winningStrategies;}
          public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
              this.winningStrategies = winningStrategies;
              return this;
          }

          public Game build(){
              validateNumberOfPlayers();
              //validateBotCount() Homework
              //validateUniqueSymbol() Players should not have same symbol
              return new Game(size, players, winningStrategies);
          }

          private void validateNumberOfPlayers(){
              if(this.players.size() >= this.size){
                  throw new InvalidPlayerCountException("Number of players should be less than "+ this.size);
              }
          }
      }
}
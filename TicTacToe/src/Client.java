import controller.GameController;
import models.*;
import models.enums.BotDifficulty;
import models.enums.GameState;
import strategy.ColWinningStrategy;
import strategy.EasyPlayingStrategy;
import strategy.RowWinningStrategy;
import strategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        int size = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Human(1,"Kunal", new Symbol("X","X"), 26));
        players.add(new Human(2,"Goku", new Symbol("O","O"), 50));
        // players.add(new Bot(2,"Goku", new Symbol("O","O"), BotDifficulty.EASY));
        // players.add(new Human(2,"Goku", new Symbol("O","O"), 50));
        //  players.add(new Human(2,"Vegeta", new Symbol("C","O"), 50));
        List<WinningStrategy> winningStrategies = List.of(new RowWinningStrategy(), new ColWinningStrategy());

        GameController gameController = new GameController();
        Game game = gameController.startGame(size, players, winningStrategies);
       // gameController.display(game);

        //play
        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
          //input
          //makeMove
          //checkWinner
          //if winner update gamestate -> Completed
          //keep on playing
            gameController.display(game); //at every move to display the board
            gameController.makeMove(game);
            // System.out.println("Do you want to undo?");
        }

        gameController.display(game); //displaying board at the end.

        if(gameController.getGameState(game).equals(GameState.DRAW)){
            System.out.println("Game Drawn");
        }

        else{ //winning
            System.out.println("Player " +game.getWinner().getName() +" has won");
        }
        //undo function
    } }
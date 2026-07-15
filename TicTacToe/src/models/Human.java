package models;

import models.enums.PlayerType;

import java.util.Scanner;

public class Human extends Player{
    private int age;
    private Scanner scanner = new Scanner(System.in);

//    public Human(int id, String name, Symbol symbol, PlayerType playerType, int age) {
    public Human(int id, String name, Symbol symbol, int age) {
       // super(id, name, symbol, playerType);
        super(id, name, symbol, PlayerType.HUMAN);
        this.age = age;
    }

    @Override
    public Move makeMove(Game game){
        System.out.println("Please enter row and column to make a move");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        validateMove(row, col);
        return new Move(this, new Cell(row, col)); //creating new Move object. the constructor in Move class takes these 2 parameters
    }

    private void validateMove(int row, int col){
        //check if cell is filled
        //check if the input is out of bounds of grid

    }

}

package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
         final  char USER = 'U';
         final   char AI = 'A';

        BattleShip battleShip = new BattleShip();
        battleShip.displayGrid(USER,AI);

        initializePlayerShips(battleShip,USER,false);
        initializePlayerShips(battleShip,AI,true);

        while (battleShip.isGameOver()){
            System.out.println("YOUR TURN");
            doUserTurn(battleShip,AI,USER,false);
            battleShip.displayGrid(USER,AI);

            System.out.println("Ai Turn");
            doUserTurn(battleShip,USER,AI,true);
            battleShip.displayGrid(USER,AI);
        }

    }

    private static void doUserTurn(BattleShip battleShip, char enemy, char self, Boolean isAi) {

        int x = getInputX(isAi);
        int y = getInputY(isAi);
        battleShip.handleGuesses(x,y,enemy,self,isAi);
    }

    private static void initializePlayerShips( BattleShip battleShip, char player, boolean isAi) {
        for (int i = 0; i < 2; ){
            int x = getInputX(isAi);
            int y = getInputY(isAi);
            i += intialzeShips(battleShip,x,y,player,isAi);
            if (isAi){
                System.out.println( i + ". Ship Deployed at x: " + x + " y: " + y);
            }
        }
    }

    private static int intialzeShips(BattleShip battleShip, int x, int y, char player, boolean isAi){
        if (battleShip.validateInput(x,y)){
            battleShip.deployShip( player,  x,  y,isAi);
            return 1;
        }else {
            System.out.println("not valid input " + x + " "+  y);
            System.out.println("Enter Coordinate Again");
           return  0;
        }
    }

    private static int getInputX( boolean isAi) {
        if (isAi){
            return random.nextInt(9);
        }
        System.out.print("Enter X coordinate for your ship: ");
        return scanner.nextInt();
    }

    private static int getInputY( boolean isAi) {
        if (isAi){
            return random.nextInt(9);
        }
        System.out.print("Enter Y coordinate for your ship: ");
        return scanner.nextInt();
    }
}

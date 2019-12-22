package com.company;

public class BattleShip {

    private char[][] grid = new char[10][10];

    private int userShipRemaining;
    private int aiShipRemaining;


    public BattleShip(){
        for (int i = 0; i < 10; i ++){
            for (int j= 0; j < 10; j++){
                grid[i][j] = ' ';
            }
        }
    }


     void displayGrid(char user, char ai){
        System.out.println("  0123456789");
        for (int i = 0; i < grid.length;i++){
            System.out.print(i + "|");
           for (int j = 0; j <grid[i].length; j++){
               if (grid[i][j] == user || grid[i][j] == ai){
                   System.out.print(' ');
               }else {
                   System.out.print(grid[i][j]);
               }
           }
           System.out.println("|" + i);
        }
        System.out.println("  0123456789");
        System.out.println("Your Ships: " + userShipRemaining + " | Computer Ships: " + aiShipRemaining);
        System.out.println();
    }

    public void deployShip(char player, int x , int y, boolean isAI){
        grid[x][y] = player;
        if (isAI){
            aiShipRemaining++;
        }else {
            userShipRemaining++;
        }
    }

    public Boolean validateInput(int x, int y) {
        if (checkIfCoordinateValid(x,y)){
            return grid[x][y] == ' ';
        }
        return false;
    }

    private Boolean checkIfCoordinateValid(int x, int y){
        if (x < 0 || x > 9)return  false;
        return y >= 0 && y <= 9;
    }

    public char[][] getGrid() {
        return grid;
    }

     void handleGuesses(int x, int y, char enemy, char self, boolean isAi) {
         final char HIT = '!';
         final char SELF_DESTRUCTION = '@';
         final char MISSED = '-';

        if (checkIfCoordinateValid(x,y)){
            if (grid[x][y] == enemy){
                updateGridAfterGuess("Boom! ship sunk!",x,y,HIT);
                updateShipQuantity(isAi,"enemy");
            }else if (grid[x][y] == self){
                updateGridAfterGuess("Oh no, you sunk your own ship :(",x,y,SELF_DESTRUCTION);
                updateShipQuantity(isAi,"self");
            }else if (grid[x][y] == ' '){
                updateGridAfterGuess("Sorry you missed",x,y,MISSED);
            }else{
                System.err.println("Something Exist at that location " + grid[x][y]);
            }
        }
    }

    private void updateShipQuantity(boolean isAi, String whoToDecrease) {
        if ("enemy".equals(whoToDecrease)){
            if (isAi){
                System.out.println("Player Lost Ship");
                userShipRemaining--;
            }else {
                System.out.println("ai Lost Ship");
                aiShipRemaining--;
            }
        }else if ("self".equals(whoToDecrease)){
            if (isAi){
                System.out.println("Ai Sunk their own ship");
                aiShipRemaining--;
            }else {
                System.out.println("Player Sunk their own ship");
                userShipRemaining--;
            }
        }
    }

    private void updateGridAfterGuess(String msg, int x, int y, char update){
        System.out.println(msg);
        grid[x][y] = update;
    }

     Boolean isGameOver(){
        return userShipRemaining > 0 && aiShipRemaining > 0;
    }

}

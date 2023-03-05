package com.losing_tictactoe.app;

import java.util.Scanner;

public class LosingTicTacToeDriver {
    public static void main(String[] args) {
        TicTacToeBoard board = new TicTacToeBoard();

        Scanner in = new Scanner(System.in);
        int userChoice = 0;
        Player user = new Player("X");
        Player bot = new Player("O");

        System.out.println("You are: " + user.getPiece());
        System.out.println("Bot is : " + bot.getPiece());
        boolean playerTurn = true;

        board.display();
        while(!board.isWon() && board.getAvailable().size() > 0) {
            if(playerTurn) {
                do {
                    System.out.print("Choose a position, 0 to quit: ");
                    userChoice = in.nextInt();
                } while(!board.valid(userChoice) && userChoice != 0);

                if(userChoice == 0) {
                    break;
                }

                board.choose(user, userChoice);
                playerTurn = false;
            } else {
                board.choose(bot, bot.randomPos(board.getAvailable()));
                playerTurn = true;

            }
            board.display();
        }
        in.close();

        if(user.winner) {
            System.out.println("You won!");
        } else if(userChoice != 0) {
            System.out.println("Bot won!");
        } else {
            System.out.println("Quitting...");
        }
    }
}
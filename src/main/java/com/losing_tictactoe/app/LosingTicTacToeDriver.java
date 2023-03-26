package com.losing_tictactoe.app;

import java.util.Scanner;

public class LosingTicTacToeDriver {
    public static void main(String[] args) {
        TicTacToeBoard board = new TicTacToeBoard();
        Scoreboard scoreboard = new Scoreboard();
        board.addScoreboard(scoreboard);

        Scanner in = new Scanner(System.in);
        int userChoice = 0;
        Player user = new Player("Player", "X");
        Player bot = new Player("Bot", "O");
        scoreboard.addPlayer(user.getName());
        scoreboard.addPlayer(bot.getName());

        System.out.println("You are: " + user.getPiece());
        System.out.println("Bot is : " + bot.getPiece());
        boolean playerTurn = true;

        boolean playAgain = true;
        while(playAgain) {
            board.display();
            while(!board.isWon() && board.getAvailable().size() > 0) {
                if(playerTurn) {
                    do {
                        System.out.print("Choose a position, 0 to quit: ");
                        userChoice = in.nextInt();
                        in.nextLine();
                    } while(!board.valid(userChoice) && userChoice != 0);

                    if(userChoice == 0) {
                        playAgain = false;
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

            if(playAgain) {
                if(user.isWinner()) {
                    System.out.println("You won!");
                    user.setWinner(false);
                } else if(userChoice != 0) {
                    System.out.println("Bot won!");
                    bot.setWinner(false);
                }
                scoreboard.displayScore();

                System.out.print("Play again? (Y/N): ");
                String answer = in.nextLine();
                if(answer.equals("y")) {
                    playAgain = true;
                    board.reset();
                    playerTurn = true;
                } else {
                    playAgain = false;
                }
            }
        }

        System.out.println("Quitting...");
        in.close();
    }
}
package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class LosingTicTacToeDriver {
    public static void main(String[] args) {
        Player user = new Human("Player", "X");
        ArrayList<Player> players = new ArrayList<>();
        players.add(user);

        Scoreboard scoreboard = new Scoreboard();
        TicTacToeBoard board = new TicTacToeBoard(players, scoreboard);

        Scanner in = new Scanner(System.in);
        int userChoice = 0;

        System.out.println("Choose an opponent:");
        System.out.printf("%s: %d", "RandomBot", 1);
        System.out.printf("%n%s: %d%n", "LearningBot", 2);
        userChoice = in.nextInt();

        Player bot;
        if(userChoice == 2) {
            LearningTree lt = new LearningTree(board.getAvailable());
            Queue<Integer> q = new LinkedList<Integer>();
            bot = new LearningBot("LearningBot", "O", lt, false, q);
        } else {
            bot = new RandomBot("RandomBot", "O");
        }
        board.addPlayer(bot);

        scoreboard.addPlayer(user.getName());
        scoreboard.addPlayer(bot.getName());

        userChoice = 0;  
        System.out.println("You are: " + user.getPiece());
        System.out.println(bot.getName() + " is : " + bot.getPiece());
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
                    } while(!board.validMove(userChoice) && userChoice != 0);

                    if(userChoice == 0) {
                        playAgain = false;
                        break;
                    }

                    ArrayList<Integer> choice = new ArrayList<>();
                    choice.add(userChoice);
                    board.choose(user, user.pickPosition(choice));
                    playerTurn = false;
                } else {
                    board.choose(bot, bot.pickPosition(board.getAvailable()));
                    playerTurn = true;
                }
                board.display();
            }

            if(playAgain) {
                if(user.isWinner()) {
                    System.out.println("You won!");
                } else if(bot.isWinner()) {
                    System.out.println("Bot won!");
                } else if(userChoice != 0) {
                    System.out.println("It's a draw!");
                }
                scoreboard.displayScore();

                System.out.print("Play again? (Y/N): ");
                String answer = in.nextLine().toLowerCase();
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
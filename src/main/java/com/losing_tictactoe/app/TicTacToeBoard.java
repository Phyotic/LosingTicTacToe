package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeBoard {
    private String[][] board;
    private ArrayList<Integer> available;
    private boolean won = false;

    TicTacToeBoard() {
        board = new String[3][3];
        available = new ArrayList<Integer>(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
            );
        
        int id = 1;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[j][i] = String.valueOf(id);
                id++;
            }
        }
    }

    public ArrayList<Integer> getAvailable() {
        for(int num : available) {
            System.out.println("Numbers in available: " + num);
        }
        return this.available;
    }

    public void display() {
        System.out.println();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                System.out.printf("%.3s", board[j][i]);

                if(j + 1 < 3) {
                    System.out.printf("%.3s", " | ");
                }
            }

            if(i + 1 < 3) {
                System.out.printf("%n%.10s%n", "________________________________");
            }
        }
        System.out.printf("%n%n");
    }

    public boolean valid(int pos) {
        return available.contains(pos);
    }

    public void choose(Player player, int pos) {
        int indexOf = available.indexOf(pos);
        available.remove(indexOf);
        int col = (pos - 1) % 3;
        int row;
        
        if(pos % 3 == 0) {
            row = (pos - 3) / 3;
        } else {
            row = (pos - (pos % 3)) / 3;
        }
        board[col][row] = player.mark();
    }

    public boolean isWon() {
        //TODO: check for win condition
        //TODO: end game when no more moves
        return won;
    }
}
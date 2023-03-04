package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeBoard {
    private String[][] board;
    private ArrayList<Integer> available;

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

    public boolean choose(Player player, int pos) {
        if(available.contains(pos)) {
            available.remove(pos);
            int col = pos / 3;
            int row = pos % 3 - 1;

            board[col][row] = player.mark();
            return true;
        } else {
            return false;
        }
    }
}

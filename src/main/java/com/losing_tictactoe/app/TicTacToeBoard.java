package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeBoard {
    private String[][] board;
    private ArrayList<Integer> available;
    private boolean finished = false;

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

    public boolean isFinished() {
        return finished;
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

    public void choose(int choice) {

    }
}

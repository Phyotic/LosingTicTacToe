package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeBoard {
    private String[][] board;
    private ArrayList<Integer> available;
    private boolean won = false;
    private Scoreboard scoreboard;

    TicTacToeBoard() {
        board = new String[3][3];
        scoreboard = null;
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
        board[col][row] = player.getPiece();
        
        if(checkWon(col, row, player)) {
            this.scoreboard.incScore(player.getName());
        }
    }

    public void setWon(boolean status) {
        this.won = status;
    }

    public boolean isWon() {
        return won;
    }

    public boolean checkWon(int col, int row, Player player) {
        int columns = 0;
        for(int c = 0; c < 3; c++) {
            if(board[c][row].equals(player.getPiece())) {
                columns++;
            }
        }

        int rows = 0;
        for(int r = 0; r < 3; r++) {
            if(board[col][r].equals(player.getPiece())) {
                rows++;
            }
        }

        int declineDiagonal = 0;
        int curCol = col, curRow = row;
        while(curCol >= 0 && curRow >= 0) {
            if(board[curCol][curRow].equals(player.getPiece())) {
                declineDiagonal++;
            }
            curCol--;
            curRow--;
        }

        curCol = col + 1;
        curRow = row + 1;
        while(curCol < 3 && curRow < 3) {
            if(board[curCol][curRow].equals(player.getPiece())) {
                declineDiagonal++;
            }
            curRow++;
            curCol++;
        }
        
        int inclineDiagonal = 0;
        curCol = col;
        curRow = row;
        while(curCol < 3 && curRow >= 0) {
            if(board[curCol][curRow].equals(player.getPiece())) {
                inclineDiagonal++;
            }
            curCol++;
            curRow--;
        }

        curCol = col - 1;
        curRow = row + 1;
        while(curCol >= 0 && curRow < 3) {
            if(board[curCol][curRow].equals(player.getPiece())) {
                inclineDiagonal++;
            }
            curCol--;
            curRow++;
        }

        if(rows == 3 || columns == 3 || declineDiagonal == 3 || inclineDiagonal == 3) {
            this.setWon(true);
            player.setWinner(true);
            return true;
        } else {
            this.setWon(false);
            return false;
        }
    }

    public void addScoreboard(Scoreboard sb) {
        scoreboard = sb;
    }

    public void reset() {
        this.setWon(false);

        int id = 1;
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                board[j][i] = String.valueOf(id);
                id++;
            }
        }

        available = new ArrayList<Integer>(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
            );
    }
}
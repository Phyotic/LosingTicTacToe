package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Arrays;

public class TicTacToeBoard implements Observable {
    private String[][] board;
    private ArrayList<Integer> available;
    private boolean won = false;
    private Scoreboard scoreboard;
    private ArrayList<Player> players;
    private boolean changed = false;
    private Player winner;

    TicTacToeBoard(ArrayList<Player> players, Scoreboard sb) {
        board = new String[3][3];
        scoreboard = sb;
        this.players = players;
        winner = null;

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

    public boolean validMove(int pos) {
        return available.contains(pos);
    }

    public void choose(Player player, int pos) {
        int indexOf = available.indexOf(pos);
        available.remove(indexOf);
        setChanged(true);
        notfifyObservers(pos);

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
            winner = player;
            return true;
        } else {
            //this.setWon(false);
            return false;
        }
    }

    public void reset() {
        for(int i = 0, size = players.size(); i < size; i++) {
            players.get(i).newGame(winner);
        }
        
        if(winner != null) {
            winner.setWinner(false);
            winner = null;
        }
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

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void addPlayer(Player player) {
        this.getPlayers().add(player);
    }

    @Override
    public void addObserver(Object o) {
        players.add((Player) o);
    }

    @Override
    public void deleteObserver(Object o) {
        players.remove((Player) o);
    }

    @Override
    public void notfifyObservers(Object o) {
        if(changed) {
            for(int i = 0, size = players.size(); i < size; i++) {
                players.get(i).update((int) o);
            }
            changed = false;
        }
    }

    @Override
    public void setChanged(boolean state) {
        changed = true;
    }

    
}
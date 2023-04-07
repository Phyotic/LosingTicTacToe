package com.losing_tictactoe.app;

import java.util.ArrayList;

public abstract class Player implements Observer {
    String piece;
    String name;
    boolean winner = false;
    int lastMove;

    Player(String name, String piece) {
        this.piece = piece;
        this.name = name;
    }

    public String getPiece() {
        return piece;
    }

    public String getName() {
        return this.name;
    }

    public void setWinner(boolean won) {
        this.winner = won;
    }

    public boolean isWinner() {
        return winner;
    }

    public int getLastMove() {
        return this.lastMove;
    }

    public void setLastMove(int position) {
        this.lastMove = position;
    }

    public abstract void newGame(Player winner);

    public abstract int pickPosition(ArrayList<Integer> available);
}

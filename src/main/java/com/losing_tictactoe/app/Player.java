package com.losing_tictactoe.app;

import java.util.ArrayList;

public abstract class Player {
    String piece;
    String name;
    boolean winner = false;

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

    public abstract int pickPosition(ArrayList<Integer> available);
}

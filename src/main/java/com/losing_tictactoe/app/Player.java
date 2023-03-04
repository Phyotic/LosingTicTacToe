package com.losing_tictactoe.app;

public class Player {
    String piece;

    Player(String piece) {
        this.piece = piece;
    }

    public String mark() {
        return piece;
    }
}

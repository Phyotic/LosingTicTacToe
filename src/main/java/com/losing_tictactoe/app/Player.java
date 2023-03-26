package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Random;

public class Player {
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

    public int randomPos(ArrayList<Integer> available) {
        Random rng = new Random();
        int randomIndex = rng.nextInt(available.size());
        int position = available.get(randomIndex);
        System.out.println("Bot chose position: " + position);
        return position;
    }

    public void setWinner(boolean won) {
        this.winner = won;
    }

    public boolean isWinner() {
        return winner;
    }

    public boolean getWinner() {
        return this.winner;
    }
}

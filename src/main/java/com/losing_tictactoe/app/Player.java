package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Random;

public class Player {
    String piece;

    Player(String piece) {
        this.piece = piece;
    }

    public String mark() {
        return piece;
    }

    public int randomPos(ArrayList<Integer> available) {
        Random rng = new Random();
        int randomIndex = rng.nextInt(available.size());
        int position = available.get(randomIndex);
        System.out.println("Bot chose position: " + position);
        return position;
    }
}

package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Random;

public class RandomBot extends Player{
    public RandomBot(String name, String piece) {
        super("RandomBot", piece);
    }

    public int pickPosition(ArrayList<Integer> available) {
        Random rng = new Random();
        int randomIndex = rng.nextInt(available.size());
        int position = available.get(randomIndex);
        this.lastMove = position;
        return position;
    }

    @Override
    public void update(Object data) {
        if(this.getLastMove() == (int) data) {
            System.out.println(this.getName() + " chose position: " + (int) data);
        }
    }

    @Override
    public void newGame(Player winner) {
        
    }
}
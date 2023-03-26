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
        System.out.println(this.getName() + " chose position: " + position);
        return position;
    }
}

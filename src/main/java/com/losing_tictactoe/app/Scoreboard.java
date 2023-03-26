package com.losing_tictactoe.app;

import java.util.HashMap;

public class Scoreboard {
    HashMap<String, Integer> score;

    Scoreboard() {
        score = new HashMap<>();
    }

    public void addPlayer(String name) {
        score.put(name, 0);
    }

    public int getPlayerScore(String name) {
        if(score.containsKey(name)) {
            return score.get(name);
        } else {
            return -1;
        }
    }

    public int incScore(String name) {
        if(score.containsKey(name)) {
            score.put(name, score.get(name) + 1);
            return score.get(name);
        } else {
            return -1;
        }
    }

    public void displayScore() {
        for(String name : score.keySet()) {
            System.out.printf("%-10s : %d%n", name, score.get(name));
        }
        System.out.println();
    }
}

package com.losing_tictactoe.app;

import java.util.ArrayList;

public class Human extends Player{
    public Human(String name, String piece) {
        super(name, piece);
    }

    public int pickPosition(ArrayList<Integer> available) {
        this.setLastMove(available.get(0));
        return available.get(0);
    }

    @Override
    public void update(Object data) {
        if(this.getLastMove() == (int) data) {
            System.out.printf("You chose %d.", (int) data);
        }
    }

    @Override
    public void newGame(Player winner) {
        
    }
}

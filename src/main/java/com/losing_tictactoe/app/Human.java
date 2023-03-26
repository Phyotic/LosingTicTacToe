package com.losing_tictactoe.app;

import java.util.ArrayList;

public class Human extends Player{
    public Human(String name, String piece) {
        super(name, piece);
    }

    public int pickPosition(ArrayList<Integer> available) {
        return available.get(0);
    }
}

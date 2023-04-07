package com.losing_tictactoe.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class LearningBot extends Player {
    LearningTree root;
    LearningTree currentLT;
    Boolean first;
    int oppLastMove;
    Queue<Integer> moveList;
    ArrayList<Integer> availableMoves;

    LearningBot(String name, String piece, LearningTree lt, Boolean first, Queue<Integer> moveList) {
        super("Learning Bot", piece);
        this.currentLT = lt;
        this.root = lt;
        this.first = first;
        this.moveList = moveList;

        availableMoves = new ArrayList<Integer>(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
            );
    }

    public int pickPosition(ArrayList<Integer> available) {
        Random rng = new Random();
        int randomIndex = rng.nextInt(available.size());
        int position = available.get(randomIndex);

        if(!(getMoveList().size() <= 1)) {
            int winPosition = currentLT.peek(position, first);

            if(winPosition > 0) {
                int index = available.indexOf(winPosition);
                position = available.get(index);
            }
        }

        setLastMove(position);
        return position;
    }

    public int getOppLastMove() {
        return this.oppLastMove;
    }

    public void setOppLastMove(int move) {
        this.oppLastMove = move;
    }

    public Queue<Integer> getMoveList() {
        return this.moveList;
    }

    public LearningTree getCurrentLT() {
        return this.currentLT;
    }

    public ArrayList<Integer> getAvailableMoves() {
        return this.availableMoves;
    }

    public void setAndDetermineWinStatus(Player winner, LearningTree lt) {
        if(winner != null) {
            if(winner.getName().equals(name)) {
                if(first) {
                    lt.setFirstWon(true);
                } else {
                    lt.setSecondWon(true);
                }
            } else {
                if(first) {
                    lt.setSecondWon(true);
                } else {
                    lt.setFirstWon(true);
                }
            }
        }
    }

    @Override
    public void update(Object data) {
        int move = (int) data;
        moveList.add(move);
        getAvailableMoves().remove(getAvailableMoves().indexOf(move));

        if(lastMove == move) {
            System.out.printf("%s chose %d.%n", name, lastMove);
        } else {
            setOppLastMove(move);
        }

        currentLT = currentLT.chose(move, getAvailableMoves());
    }

    @Override
    public void newGame(Player winner) {
        setAndDetermineWinStatus(winner, currentLT);

        LearningTree anchor;
        int[] backtrack = new int[2];
        ArrayList<Integer> posMoves = new ArrayList<Integer>(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
        );

        currentLT = root;

        int pos;
        while(moveList.size() > 2) {
            pos = moveList.remove();
            posMoves.remove(posMoves.indexOf(pos));
            currentLT = currentLT.chose(pos, posMoves);
        }
        moveList.remove();
        anchor = currentLT;

        while(availableMoves.size() > 0) {
            pos = availableMoves.get(0);
            backtrack[0] = pos;
            availableMoves.remove(0);
            posMoves.remove(posMoves.indexOf(pos));
            currentLT = currentLT.chose(pos, posMoves);

            pos = moveList.remove();
            backtrack[1] = pos;
            posMoves.remove(posMoves.indexOf(pos));
            currentLT = currentLT.chose(pos, posMoves);

            setAndDetermineWinStatus(winner, currentLT);

            posMoves.add(backtrack[0]);
            posMoves.add(backtrack[1]);
            moveList.add(backtrack[1]);
            currentLT = anchor;
        }

        currentLT = root;
        oppLastMove = 0;
        moveList.clear();
        availableMoves = new ArrayList<>(
            Arrays.asList(1,2,3,4,5,6,7,8,9)
        );
    }
}
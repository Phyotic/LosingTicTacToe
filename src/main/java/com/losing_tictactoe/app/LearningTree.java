package com.losing_tictactoe.app;

import java.util.ArrayList;

public class LearningTree {
    private int position;
    private boolean firstWon;
    private boolean secondWon;
    private LearningTree[] legalMoves;

    public LearningTree(ArrayList<Integer> possibleMoves) {
        legalMoves = new LearningTree[possibleMoves.size()];

        ArrayList<Integer> posMoves = new ArrayList<>();
        posMoves.addAll(possibleMoves);

        for(int i = 0, size = posMoves.size(); size > i; i++) {
            int childPosition = posMoves.get(0);
            posMoves.remove(0);
            legalMoves[i] = new LearningTree(posMoves, childPosition);
            posMoves.add(childPosition);
        }
    }

    public LearningTree(ArrayList<Integer> possibleMoves, int position) {
        this.position = position;
        legalMoves = new LearningTree[possibleMoves.size()];
    }

    public int getPosition() {
        return position;
    }

    public boolean isFirstWon() {
        return firstWon;
    }

    public void setFirstWon(boolean state) {
        firstWon = state;
    }

    public boolean isSecondWon() {
        return secondWon;
    }

    public void setSecondWon(boolean state) {
        secondWon = state;
    }

    public LearningTree[] getLegalMoves() {
        return this.legalMoves;
    }

    public LearningTree chose(int position, ArrayList<Integer> available) {
        int indexOfChild = 0;
        boolean exists = false;

        for(int i = 0, size = legalMoves.length; i < size; i++) {
            if(null == legalMoves[i]) {
                indexOfChild = i;
            }
            if(null != legalMoves[i] && legalMoves[i].getPosition() == position) {
                indexOfChild = i;
                exists = true;
                break;
            }
        }

        if(exists) {
            return this.legalMoves[indexOfChild];
        } else {
            legalMoves[indexOfChild] = new LearningTree(available, position);
            return legalMoves[indexOfChild];
        }
    }

    public int peek(int position, boolean first) {
        int shouldPick = 0;
        boolean exists = false;
        int indexOfPosition = 0;

        for(int i = 0, size = legalMoves.length; i < size; i++) {
            if(null != legalMoves[i] && legalMoves[i].getPosition() == position) {
                exists = true;
                indexOfPosition = i;
                break;
            }
        }

        if(exists) {
            LearningTree chosenLT = legalMoves[indexOfPosition];
            LearningTree[] chosenLegalMoves = chosenLT.getLegalMoves();

            for(int i = 0, size = chosenLegalMoves.length; i < size; i++) {
                if(null != chosenLegalMoves[i]) {
                    LearningTree nextMove = chosenLegalMoves[i];

                    if(first && nextMove.isSecondWon() || !first && nextMove.isFirstWon()) {
                        shouldPick = nextMove.getPosition();
                    }
                }
            }
        }
        return shouldPick;
    }
}

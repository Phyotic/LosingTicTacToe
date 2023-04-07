package com.losing_tictactoe.app;

public interface Observable { 
    public void addObserver(Object o);

    public void deleteObserver(Object o);

    public void notfifyObservers(Object o);

    public void setChanged(boolean state);
}

package com.codecool.gameoflife.game;

public class Rules {
    public boolean nextState(boolean alive, int numberOfNeighbours) {
        if (numberOfNeighbours == 3 || (alive && numberOfNeighbours == 2))
            return true;
        return false;
    }
}
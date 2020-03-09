package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;


public class Skeleton extends Actor {
    private int[][] possibleMovements = {{0,-1},{0,1},{-1,0},{1,0}};
    public Skeleton(Cell cell) {
        super(cell,2,10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


}

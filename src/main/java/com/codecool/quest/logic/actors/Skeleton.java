package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Skeleton extends Actor {
    public Skeleton(Cell cell) {
        super(cell,2,10);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


}

package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

import java.util.ArrayList;
import java.util.List;

public class Skeleton extends Actor {
    public static List<Actor> allSkeletons = new ArrayList<>();

    public Skeleton(Cell cell) {
        super(cell,2,10);
        allSkeletons.add(this);
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }


}

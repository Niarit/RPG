package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Zalgotrax extends Actor {

    public Zalgotrax(Cell cell) {
        super(cell, 0, 100);
    }

    @Override
    public String getTileName() {
        return "zalgotrax";
    }
}

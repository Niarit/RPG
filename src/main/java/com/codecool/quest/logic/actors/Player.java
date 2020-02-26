package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell,5,10);
    }

    public String getTileName() {
        return "player";
    }

}

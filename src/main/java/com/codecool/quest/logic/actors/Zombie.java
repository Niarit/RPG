package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;


public class Zombie extends Actor {

    public Zombie (Cell cell) {
        super(cell,1,5);
    }

    @Override
    public String getTileName() {
        return "zombie";
    }


}

package com.codecool.quest.logic.doors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Player;

public class BlueDoor extends Door {

    public BlueDoor(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        super.pickUp(player);
    }

    @Override
    public String getTileName() {
        return "blue_closed_door";
    }
}

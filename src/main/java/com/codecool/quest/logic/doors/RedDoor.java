package com.codecool.quest.logic.doors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.doors.Door;

public class RedDoor extends Door {

    public RedDoor(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        super.pickUp(player);
    }

    @Override
    public String getTileName() {
        return "red_closed_door";
    }
}
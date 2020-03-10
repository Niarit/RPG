package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Player;

public class Door extends Item {

    public Door(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        super.pickUp(player);
    }

    @Override
    public String getTileName() {
        return "closed_door";
    }
}

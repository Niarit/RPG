package com.codecool.quest.logic.items;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Player;

public class Key extends Item{


    public Key(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        if (Main.items.size() < 9) {
            Main.items.add("key");
            super.pickUp(player);
        }
    }

    @Override
    public String getTileName() {
        return "key";
    }
}

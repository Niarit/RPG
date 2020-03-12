package com.codecool.quest.logic.items.keys;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Dialogue;
import com.codecool.quest.logic.actors.Player;

public class BlueKey extends Key {


    public BlueKey(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        if (Main.items.size() < 9) {
            Main.items.add("blueKey");
            new Dialogue("Picked up a key");
            super.pickUp(player);
        }
    }

    @Override
    public String getTileName() {
        return "blueKey";
    }
}

package com.codecool.quest.logic.items.keys;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Dialogue;
import com.codecool.quest.logic.actors.Player;

import java.util.Date;

public class RedKey extends Key {


    public RedKey(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        if (Main.items.size() < 9) {
            Main.items.add("redKey");
            new Dialogue("Picked up a key");
            super.pickUp(player);
        }
    }

    @Override
    public String getTileName() {
        return "redKey";
    }
}
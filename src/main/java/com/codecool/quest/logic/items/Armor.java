package com.codecool.quest.logic.items;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Player;

public class Armor extends Item {
    int health = 5;
    public Armor(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        player.setHealth(player.getHealth()+health);
        Main.items.add("armor");
        super.pickUp(player);
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}

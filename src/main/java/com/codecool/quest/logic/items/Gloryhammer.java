package com.codecool.quest.logic.items;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Dialogue;
import com.codecool.quest.logic.actors.Player;

public class Gloryhammer extends Item {
    int damage = 200;
    int health = 200;

    public Gloryhammer(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        if (Main.items.size() < 9) {
            new Dialogue("Picked up Gloryhammer");
            player.setDamage(player.getDamage() + damage);
            player.setHealth(player.getHealth() + health);
            Main.items.add("Gloryhammer");
            super.pickUp(player);
        }
    }

    @Override
    public String getTileName() {
        return "Gloryhammer";
    }
}

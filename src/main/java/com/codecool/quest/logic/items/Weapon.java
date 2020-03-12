package com.codecool.quest.logic.items;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Dialogue;
import com.codecool.quest.logic.actors.Player;

public class Weapon extends Item {
    int damage = 5;
    public Weapon(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        if (Main.items.size() < 9) {
            player.setDamage(player.getDamage() + damage);
            Main.items.add("weapon");
            new Dialogue("Picked up a weapon");
            super.pickUp(player);
        }
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}

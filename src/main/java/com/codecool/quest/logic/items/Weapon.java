package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Player;

public class Weapon extends Item {
    int damage = 5;
    public Weapon(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        player.setDamage(player.getDamage()+damage);
        super.pickUp(player);
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}

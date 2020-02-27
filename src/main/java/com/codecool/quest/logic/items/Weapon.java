package com.codecool.quest.logic.items;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Player;

import java.util.ArrayList;

public class Weapon extends Item {
    public ArrayList<String> currentItem = new ArrayList<>();
    private Main main;
    int damage = 5;
    public Weapon(Cell cell) {
        super(cell);
    }

    @Override
    public void pickUp(Player player) {
        player.setDamage(player.getDamage()+damage);
        currentItem = getItems();
        currentItem.add("weapon");
        setItems(currentItem);
        super.pickUp(player);
    }

    @Override
    public String getTileName() {
        return "weapon";
    }
}

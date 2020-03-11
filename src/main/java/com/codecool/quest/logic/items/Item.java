package com.codecool.quest.logic.items;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.actors.Player;


public abstract class Item implements Drawable {
    public Cell cell;


    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }
    public void pickUp (Player player) {
        cell.setItem(null);
    }
}

package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;
import com.codecool.quest.logic.actors.Player;

import java.util.ArrayList;

public abstract class Item implements Drawable {
    private Cell cell;
    private ArrayList<String> Items = new ArrayList<>();


    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }
    public void pickUp (Player player){
        cell.setItem(null);
    }

    public ArrayList<String> getItems() {
        return Items;
    }

    public void setItems(ArrayList<String> items) {
        Items = items;
    }
}

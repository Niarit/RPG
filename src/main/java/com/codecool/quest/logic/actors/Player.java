package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.items.Item;
import com.sun.glass.ui.Clipboard;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {


    private List<Item> items = new ArrayList<>();

    public Player(Cell cell) {
        super(cell, 5, 5);
    }

    public String getTileName() {
        if (getHealth() <= 5) {
            if (getDamage() <= 5) {
                return "player1";
            } else {
                return "player2";
            }
        } else if (getHealth() <= 10) {
            if (getDamage() <= 10) {
                return "player3";
            } else{
                return "player4";
            }
        }
        return "player5";
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    @Override
    public void move(int dx, int dy) {
        Cell cell = getCell().getNeighbor(dx, dy);
        if (cell != null && cell.getItem() != null) {
            items.add(cell.getItem());
            cell.getItem().pickUp(this);
        }
        super.move(dx, dy);
    }

}


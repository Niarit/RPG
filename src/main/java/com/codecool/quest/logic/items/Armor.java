package com.codecool.quest.logic.items;

import com.codecool.quest.logic.Cell;

public class Armor extends Item {
    public Armor(Cell cell){
        super(cell);
    }

    @Override
    public String getTileName() {
        return "armor";
    }
}
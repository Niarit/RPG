package com.codecool.quest.logic.doors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.items.Item;

public abstract class Door extends Item {

    public Door(Cell cell){
        super(cell);
    }
}

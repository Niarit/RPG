package com.codecool.quest.logic.path;


import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

public abstract class BasicPath implements Drawable {
    private Cell cell;

    public BasicPath(Cell cell){
        this.cell = cell;
        this.cell.setBasicPath(this);
    }

    public abstract void goTrough();



}

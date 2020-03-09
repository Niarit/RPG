package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;

import java.util.Random;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health;
    private int damage;


    public Actor(Cell cell, int damage, int health) {
        this.cell = cell;
        this.cell.setActor(this);
        this.damage = damage;
        this.health = health;

    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        cell.setActor(null);
        nextCell.setActor(this);
        cell = nextCell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

}

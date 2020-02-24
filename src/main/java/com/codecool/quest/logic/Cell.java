package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Skeleton;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private int x, y;

    Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        Cell cellToMove = gameMap.getCell(x + dx, y + dy);
        if (cellToMove.type == CellType.WALL || cellToMove.type == CellType.EMPTY){
            return gameMap.getCell(x, y);
        } else if (cellToMove.type == CellType.FLOOR && cellToMove.getActor() != null){
            return gameMap.getCell(x,y);
        } else if (x + dx < 0 && x + dx > gameMap.getWidth()-1 && y + dy < 0 && y + dy > gameMap.getHeight()-1){
            return gameMap.getCell(x,y);
        }
        return cellToMove;

    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

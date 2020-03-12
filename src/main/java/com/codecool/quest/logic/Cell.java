package com.codecool.quest.logic;

import com.codecool.quest.Main;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.items.Item;
import com.codecool.quest.logic.path.BasicPath;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
    private GameMap gameMap;
    private int x, y;
    private BasicPath basicPath;

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

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public BasicPath getBasicPath() {
        return basicPath;
    }

    public Cell getNeighbor(int dx, int dy) {
        try {
            Cell cellToMove = gameMap.getCell(x + dx, y + dy);
            if (cellToMove.type == CellType.WALL || cellToMove.type == CellType.EMPTY || cellToMove.type == CellType.WINE || cellToMove.type == CellType.RUINED_WALL_PIECE_LEFT || cellToMove.type == CellType.RUINED_WALL_PIECE_RiGHT|| cellToMove.type == CellType.ROCK || cellToMove.type == CellType.TREE) {
                return gameMap.getCell(x, y);
            } else if (cellToMove.type == CellType.FLOOR && cellToMove.getActor() != null) {
                combat(cellToMove, gameMap.getCell(x, y));
                return gameMap.getCell(x, y);
            } else if (cellToMove.getBasicPath() != null) {
                cellToMove.getBasicPath().goTrough();
            } else if (cellToMove.getTileName().equals("water")) {
                gameMap.getCell(x, y).getActor().setHealth(gameMap.getCell(x, y).getActor().getHealth() - 5);
                return cellToMove;
            } else if (cellToMove.getTileName().equals("opened_door") && cellToMove.getItem() != null) {
                if (!Main.items.contains("key")) {
                    return gameMap.getCell(x, y);
                }
                removeItem("key");
            } else if (cellToMove.getItem() != null) {
                return cellToMove;
            }
            return cellToMove;


        } catch (ArrayIndexOutOfBoundsException e) {
            return gameMap.getCell(x, y);
        }

    }

    private void combat(Cell cellToMove, Cell playerCell) {
        cellToMove.getActor().setHealth(cellToMove.getActor().getHealth() - playerCell.getActor().getDamage());
        playerCell.getActor().setHealth(playerCell.getActor().getHealth() - cellToMove.getActor().getDamage());

        if (cellToMove.getActor().getHealth() <= 0) {
            cellToMove.setActor(null);
        }

    }

    private void removeItem(String item) {
        ArrayList<String> newItemList = new ArrayList<>();
        for (String word : Main.items) {
            if (!word.equals(item)) {
                newItemList.add(word);
            }
        }
        Main.items = newItemList;
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


    public void setBasicPath(BasicPath basicPath) {
        this.basicPath = basicPath;
    }
}

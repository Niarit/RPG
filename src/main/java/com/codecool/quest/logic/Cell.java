package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.items.Item;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private Item item;
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

    public void setItem(Item item){
        this.item = item;
    }

    public Item getItem(){
        return item;
    }

    public Cell getNeighbor(int dx, int dy) {
        try{
            Cell cellToMove = gameMap.getCell(x + dx, y + dy);
            if (cellToMove.type == CellType.WALL || cellToMove.type == CellType.EMPTY){
                return gameMap.getCell(x, y);
            } else if (cellToMove.type == CellType.FLOOR && cellToMove.getActor() != null){
                combat(cellToMove, gameMap.getCell(x,y));
                return gameMap.getCell(x,y);
            }
            return cellToMove;
        }catch (Exception e){
            return gameMap.getCell(x,y);
        }

    }

    private void combat(Cell cellToMove, Cell playerCell) {
//        int enemyDamage = cellToMove.getActor().getDamage();
//        int enemyHealth = cellToMove.getActor().getHealth();
//        int playerDamage = playerCell.getActor().getDamage();
//        int playerHealth = playerCell.getActor().getHealth();

        cellToMove.getActor().setHealth(cellToMove.getActor().getHealth() - playerCell.getActor().getDamage());
        playerCell.getActor().setHealth(playerCell.getActor().getHealth() - cellToMove.getActor().getDamage());

        if(cellToMove.getActor().getHealth() <= 0){
            cellToMove.setActor(null);
        }

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

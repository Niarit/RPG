package com.codecool.quest;

import com.codecool.quest.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player1", new Tile(25, 0));
        tileMap.put("player2", new Tile(26, 0));
        tileMap.put("player3", new Tile(27, 0));
        tileMap.put("player4", new Tile(28, 0));
        tileMap.put("player5", new Tile(31, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("weapon", new Tile(5,29));
        tileMap.put("armor",new Tile(1,23));
        tileMap.put("path",new Tile(11,1));
        tileMap.put("water", new Tile(8,4));
        tileMap.put("bridge", new Tile(7,5));
        tileMap.put("tree", new Tile(6,2));
        tileMap.put("rock", new Tile(5,2));
        tileMap.put("zalgotrax", new Tile(31,1));
        tileMap.put("ruined_wall_piece", new Tile(1,11));
        tileMap.put("false_wall", new Tile(1,2));
        tileMap.put("wine", new Tile(1,2));
        tileMap.put("Gloryhammer", new Tile(5,29));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}

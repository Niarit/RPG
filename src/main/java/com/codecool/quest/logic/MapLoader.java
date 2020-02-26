package com.codecool.quest.logic;

import com.codecool.quest.Main;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.items.Armor;
import com.codecool.quest.logic.items.Weapon;
import com.codecool.quest.logic.path.BasicPath;
import com.codecool.quest.logic.path.PathWay;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class MapLoader {
    private final Main main;
    public MapLoader(Main main) {
        this.main = main;
    }
    public void refreshStage(){
        main.refresh();
    }

    public GameMap loadMap(String mapFile) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFile);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Weapon(cell);
                            break;
                        case 'a':
                            cell.setType(CellType.FLOOR);
                            new Armor(cell);
                            break;
                        case 'p':
                            cell.setType(CellType.PATH);
                            cell.setBasicPath(new PathWay(cell,this));
                            break;
                        case 'l':
                            cell.setType(CellType.WATER);
                            break;
                        case 't':
                            cell.setType(CellType.TREE);
                            break;
                        case 'r':
                            cell.setType(CellType.ROCK);
                            break;
                        case 'b':
                            cell.setType(CellType.BRIDGE);
                            break;
                        case 'z':
                            cell.setType(CellType.ZALGOTRAX);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        main.map = map;
        return map;
    }

}

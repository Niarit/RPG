package com.codecool.quest.logic;

import com.codecool.quest.Main;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.actors.Zalgotrax;
import com.codecool.quest.logic.items.Armor;
import com.codecool.quest.logic.items.Weapon;
import com.codecool.quest.logic.path.PathWay;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public Player currentPlayer;
    private final Main main;
    public MediaPlayer mediaPlayer;

    public MapLoader(Main main) {
        this.main = main;
    }
    public void refreshStage(){
        main.refresh();
    }


    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public GameMap loadMap(String mapFile, String musicFile) {
        Media media = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

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
                            if(currentPlayer == null) {
                                currentPlayer = new Player(cell);
                            }else {
                                Player player = new Player(cell);
                                player.setHealth(currentPlayer.getHealth());
                                player.setDamage(currentPlayer.getDamage());
                                currentPlayer = player;
                            }
                            map.setPlayer(currentPlayer);
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
                            cell.setType(CellType.FLOOR);
                            new Zalgotrax(cell);
                            break;
                        case '_':
                            cell.setType(CellType.WINE);
                            break;
                        case '/':
                            cell.setType(CellType.RUINED_WALL_PIECE);
                            break;
                        case 'f':
                            cell.setType(CellType.FALSE_WALL);
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

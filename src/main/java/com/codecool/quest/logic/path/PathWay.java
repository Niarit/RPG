package com.codecool.quest.logic.path;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.MapLoader;

public class PathWay extends BasicPath {
    private MapLoader mapLoader;

    public PathWay(Cell cell, MapLoader mapLoader) {
        super(cell);
        this.mapLoader = mapLoader;
    }

    @Override
    public void goTrough() {
        mapLoader.loadMap("/newMap.txt", "/home/nia/codecool/OOP/2ndTW/RPG/src/main/resources/Magic Dragon.mp3");
        mapLoader.refreshStage();
    }

    @Override
    public String getTileName() {
        return "path";
    }
}

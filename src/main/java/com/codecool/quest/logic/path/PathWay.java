package com.codecool.quest.logic.path;

import com.codecool.quest.Main;
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
        mapLoader.loadMap("/newMap.txt");
        mapLoader.refreshStage();
    }

    @Override
    public String getTileName() {
        return "path";
    }
}

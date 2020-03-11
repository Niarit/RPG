package com.codecool.quest.logic.path;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.MapLoader;

public class BackTrack extends BasicPath {
    private MapLoader mapLoader;

    public BackTrack(Cell cell, MapLoader mapLoader) {
        super(cell);
        this.mapLoader = mapLoader;
    }

    @Override
    public void goTrough() {
        mapLoader.loadMap("/map.txt", System.getProperty("user.dir") + "/src/main/resources/Hootsforce.mp3");
        mapLoader.refreshStage();

    }

    @Override
    public String getTileName() {
        return "path";
    }
}

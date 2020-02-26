package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    PATH("path"),
    WATER("water"),
    BRIDGE("bridge"),
    TREE("tree"),
    ROCK("rock"),
    ZALGOTRAX("zalgotrax");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

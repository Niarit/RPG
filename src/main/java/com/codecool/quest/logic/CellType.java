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
    RUINED_WALL_PIECE_LEFT("ruined_wall_piece_left"),
    RUINED_WALL_PIECE_RiGHT("ruined_wall_piece_right"),
    WINE("wine"),
    FALSE_WALL("false_wall"),
    OPENED_DOOR("opened_door"),
    LEFT_CORNER("left_corner"),
    RIGHT_CORNER("right_corner");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}

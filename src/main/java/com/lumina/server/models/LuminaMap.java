package com.lumina.server.models;

public class LuminaMap {

    private Area[][] areas = new Area[10][10];
    static LuminaMap instance;

    public LuminaMap() {
        initializeAreas();
    }

    private void initializeAreas() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                areas[i][j] = new Area("Area " + i + "-" + j);
            }
        }

        // Example content
        areas[0][0].addObject("Golden Key");
        areas[0][0].addPerson("Sleeping Guard");
        areas[2][3].addPerson("Mysterious Old Man");
        areas[5][5].addObject("Broken Sword");
    }

    public Area getArea(int x, int y) {
        return areas[x][y];
    }

    public static  LuminaMap getInstance() {
        if (instance == null) {
            instance = new LuminaMap();
        }
        return instance;
    }

}

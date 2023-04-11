package sk.amjj.view.tiles;

import java.awt.Canvas;

import sk.amjj.view.DefaultSettings;

public abstract class Tile extends Canvas {   
    public Tile() {
        setBackground(DefaultSettings.BOARD_COLOR);
    }
}

package sk.amjj.view.swingView.board.tiles;

import java.awt.Canvas;
import java.awt.Color;

import lombok.Getter;
import sk.amjj.universalStructs.Coords;
import sk.amjj.view.DefaultSettings;

@Getter
public abstract class Tile extends Canvas {   
    Coords positon;

    private Color bgColor = DefaultSettings.BOARD_COLOR;

    public Tile(Coords position) {
        this.positon = position;
        setBackground(bgColor);
    }

    public void setBgColor(Color color) {
        this.bgColor = color;
        setBackground(color);
    }
}

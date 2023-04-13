package sk.amjj.view.swingView.board.tiles;

import java.awt.Canvas;

import lombok.Getter;
import sk.amjj.universalStructs.Coords;
import sk.amjj.view.DefaultSettings;

@Getter
public abstract class Tile extends Canvas {   
    Coords positon;

    public Tile(Coords position) {
        this.positon = position;
        setBackground(DefaultSettings.BOARD_COLOR);
    }
}

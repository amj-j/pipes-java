package sk.amjj.universalStructs;

import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.NotAPipeException;

public class BoardInfo {
    private final PipeInfo[][] tiles;

    public BoardInfo(PipeInfo[][] tiles) {
        this.tiles = tiles;
    }

    public int getRows() {
        return this.tiles[0].length;
    }

    public int getCols() {
        return this.tiles.length;
    }

    public boolean isAPipe(Coords pos) {
        return tiles[pos.getX()][pos.getY()] != null;
    }

    public PipeInfo getPipeInfo(Coords pos) throws NotAPipeException, CoordsOutOfRangeException {
        if (!pos.isInRange(new Coords(0,0), new Coords(getCols() - 1, getRows() - 1))) {
            throw new CoordsOutOfRangeException();
        }
        if (!isAPipe(pos)) {
            throw new NotAPipeException();
        }
        return tiles[pos.getX()][pos.getY()];
    }
}

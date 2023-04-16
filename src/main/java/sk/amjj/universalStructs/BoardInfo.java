package sk.amjj.universalStructs;

import lombok.AllArgsConstructor;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.NotAPipeException;

@AllArgsConstructor
public class BoardInfo {
    private final PipeInfo[][] tiles;
    private final Coords startPos;
    private final Coords endPos;

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

    public Coords getStartPos() {
        return new Coords(startPos);
    }

    public Coords getEndPos() {
        return new Coords(endPos);
    }
}

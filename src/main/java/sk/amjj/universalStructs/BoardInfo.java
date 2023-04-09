package sk.amjj.universalStructs;

public class BoardInfo {
    private PipeInfo[][] tiles;

    public BoardInfo(PipeInfo[][] tiles) {
        this.tiles = tiles;
    }

    public int getRows() {
        return this.tiles[0].length;
    }

    public int getCols() {
        return this.tiles.length;
    }
}

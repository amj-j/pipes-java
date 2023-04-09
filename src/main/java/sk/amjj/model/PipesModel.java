package sk.amjj.model;

import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.model.creators.ICreator;
import sk.amjj.model.creators.RandCreator;
import sk.amjj.model.data.Board;
import sk.amjj.model.data.Pipe;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;

public class PipesModel implements IPipesModel {
    private ICreator creator;
    private Board board;
    private int gamesWon = 0;

    public PipesModel() {
        this.creator = new RandCreator();
    }

    @Override
    public int getWonGames() {
        return this.gamesWon;
    }

    @Override
    public void addWonGame() {
        this.gamesWon++;
    }

    @Override
    public BoardInfo newBoard(int rows, int cols) {
        this.board = creator.createBoard(rows, cols);
        return this.getBoard();
    }

    @Override
    public BoardInfo getBoard() {
        PipeInfo[][] tiles = new PipeInfo[board.getCols()][board.getRows()];
        for (Pipe pipe : board.getRoute()) {
            tiles[pipe.getPos().getX()][pipe.getPos().getY()] = new PipeInfo(pipe.getSides());
        }
        return new BoardInfo(tiles);
    }

    @Override
    public PipeInfo rotatePipe(Coords coords, boolean clockwise) throws NotAPipeException, CoordsOutOfRangeException {
        if (coords.getX() < 0 || coords.getY() < 0 || coords.getX() > board.getCols() || coords.getY() > board.getRows()) {
            throw new CoordsOutOfRangeException();
        }
        Pipe pipe = null;
        for (Pipe p : board.getRoute()) {
            if (p.getPos().equals(coords)) {
                pipe = p;
                break;
            }
        }
        if (pipe == null) {
            throw new NotAPipeException();
        }
        pipe.rotate(clockwise);
        return new PipeInfo(pipe.getSides());
    }

    @Override
    public boolean arePipesAlligned() {
        for (Pipe pipe : board.getRoute()) {
            if (!pipe.isCorrectlyRotated()) {
                return false;
            }
        }
        return true;
    }
}

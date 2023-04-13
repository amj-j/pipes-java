package sk.amjj.model;

import java.util.ArrayList;

import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.model.creators.ICreator;
import sk.amjj.model.creators.RandCreator;
import sk.amjj.model.data.Board;
import sk.amjj.model.data.Pipe;
import sk.amjj.model.interfaces.IPipesModel;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;

public class PipesModel implements IPipesModel {
    private ICreator creator;
    private Board board;
    private int gamesWon = 0;

    public PipesModel() {
        this.creator = new RandCreator();
        initBoard(DefaultSettings.BOARD_ROWS, DefaultSettings.BOARD_COLS);
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
    public void resetWonGames() {
        this.gamesWon = 0;
    }

    @Override
    public int getBoardRows() {
        return this.board.getRows();
    }

    @Override
    public int getBoardCols() {
        return this.board.getCols();
    }

    @Override
    public BoardInfo initBoard(int rows, int cols) {
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
        if (!coords.isInRange(new Coords(0,0), new Coords(board.getCols() - 1, board.getRows() - 1))) {
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
    public AllignmentCorrectness getAllignmentCorrectness() {
        ArrayList<Coords> correctlyAlligned = new ArrayList<>();
        for (Pipe pipe : board.getRoute()) {
            if (pipe.isCorrectlyRotated()) {
                correctlyAlligned.add(new Coords(pipe.getPos()));
            }
            else {
                return new AllignmentCorrectness(correctlyAlligned, new Coords(pipe.getPos()));
            }
        }
        return null;
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

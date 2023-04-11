package sk.amjj.model;

import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;

public interface IPipesModel {

    int getWonGames();

    void addWonGame();

    BoardInfo newBoard(int rows, int cols);

    BoardInfo getBoard();

    /**
     * Rotates a pipe
     * @param coords - coordinates of the pipe to rotate
     * @param clockwise - true for clockwise rotation, false for counter clockwise rotation
     * @return PipeInfo object representing pipe orientation after rotation
     * @throws CoordsOutOfRangeException if given coords aren't within the current board
     * @throws NotAPipeException if given coords don't contain a pipe
     */
    PipeInfo rotatePipe(Coords coords, boolean clockwise)  throws NotAPipeException, CoordsOutOfRangeException;

    AllignmentCorrectness getAllignmentCorrectness();

    boolean arePipesAlligned();

}

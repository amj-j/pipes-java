package sk.amjj.view.interfaces;

import sk.amjj.controller.interfaces.IBoardListener;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;

public interface IBoardView {
    void setNewBoard(BoardInfo boardInfo) throws InvalidBoardInfoException;
    void rotatePipe(PipeInfo pipeInfo, Coords position) throws CoordsOutOfRangeException, NotAPipeException;
    boolean isCorresctnessHighlighted();
    void highlightCorrectness(AllignmentCorrectness allignmentCorrectness);
    void dehighlightCorrectness();
    void addBoardListener(IBoardListener listener);
}

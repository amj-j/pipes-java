package sk.amjj.view;

import sk.amjj.controller.IEventListener;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;

public interface IView {
    void setNewBoard(BoardInfo boardInfo) throws InvalidBoardInfoException;
    void rotatePipe(PipeInfo pipeInfo, Coords position) throws CoordsOutOfRangeException, NotAPipeException;
    boolean isCorresctnessHighlighted();
    void highlightCorrectness(AllignmentCorrectness allignmentCorrectness);
    void dehighlightCorrectness();
    void showWonGames(int gamesWon);
    void showBoardSize(int rows, int cols);
    void addEventListener(IEventListener listener);
    void setVisible();
}

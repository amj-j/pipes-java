package sk.amjj.view.swingView.board;

import java.awt.Container;
import java.awt.Dimension;

import sk.amjj.controller.IEventListener;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;

public interface IBoardPanel {
    void setNewBoard(BoardInfo boardInfo) throws InvalidBoardInfoException;
    void rotatePipe(PipeInfo pipeInfo, Coords position) throws CoordsOutOfRangeException, NotAPipeException;
    boolean isCorresctnessHighlighted();
    void highlightCorrectness(AllignmentCorrectness allignmentCorrectness);
    void dehighlightCorrectness();
    void addEventListener(IEventListener listener);
    void addTo(Container container);
    void setPreferredSize(Dimension d);
}

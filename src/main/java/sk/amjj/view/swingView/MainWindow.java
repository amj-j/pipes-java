package sk.amjj.view.swingView;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import sk.amjj.controller.IEventListener;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;
import sk.amjj.view.IView;
import sk.amjj.view.swingView.board.BoardPanel;
import sk.amjj.view.swingView.board.IBoardPanel;
import sk.amjj.view.swingView.menu.IMenuPanel;
import sk.amjj.view.swingView.menu.MenuPanel;

public class MainWindow extends JFrame implements IView {
    IBoardPanel board;
    IMenuPanel menu;

    public MainWindow() {
        this.board = new BoardPanel();
        this.menu = new MenuPanel();

        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.board.addTo(this);
        this.menu.addTo(this);
        this.setVisible(true);
    }

    @Override
    public void setNewBoard(BoardInfo boardInfo) throws InvalidBoardInfoException {
        board.setNewBoard(boardInfo);
    }

    @Override
    public void rotatePipe(PipeInfo pipeInfo, Coords position) throws CoordsOutOfRangeException, NotAPipeException {
        board.rotatePipe(pipeInfo, position);
    }

    @Override
    public boolean isCorresctnessHighlighted() {
        return board.isCorresctnessHighlighted();
    }

    @Override
    public void highlightCorrectness(AllignmentCorrectness allignmentCorrectness) {
        board.highlightCorrectness(allignmentCorrectness);
    }

    @Override
    public void dehighlightCorrectness() {
        board.dehighlightCorrectness();
    }

    @Override
    public void showWonGames(int gamesWon) {
        menu.showWonGames(gamesWon);
    }

    @Override
    public void showBoardSize(int rows, int cols) {
        menu.showBoardSize(rows, cols);
    }

    @Override
    public void addEventListener(IEventListener listener) {
        board.addEventListener(listener);
        menu.addEventListener(listener);
    }
}

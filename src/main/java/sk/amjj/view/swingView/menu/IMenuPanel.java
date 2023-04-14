package sk.amjj.view.swingView.menu;

import java.awt.Container;
import java.awt.Dimension;

import sk.amjj.controller.IEventListener;

public interface IMenuPanel {
    void showWonGames(int gamesWon);
    void showBoardSize(int rows, int cols);
    void addEventListener(IEventListener listener);
    void addTo(Container container);
    void setPrefferedSize(Dimension d);
}

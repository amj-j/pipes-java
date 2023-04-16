package sk.amjj.view.swingView.menu;

import java.awt.Container;
import java.awt.Dimension;

public interface IMenuPanel {
    void showWonGames(int gamesWon);
    void showBoardSize(int rows, int cols);
    void addTo(Container container);
    void setPreferredSize(Dimension d);
}

package sk.amjj.view.interfaces;

import sk.amjj.controller.interfaces.IMenuListener;

public interface IMenuView {
    void showWonGames(int gamesWon);
    void showBoardSize(int rows, int cols);
    void addMenuListener(IMenuListener listener);
}

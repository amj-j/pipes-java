package sk.amjj.controller;

import sk.amjj.universalStructs.Coords;

public interface IEventListener {
    void rotatePipe(Coords pos, boolean clockwise);
    void checkPipeAllignment();
    void changeBoardSize(int rows, int cols);
    void resetGame();
    void exitApp();
}

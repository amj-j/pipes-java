package sk.amjj.controller.interfaces;

public interface IMenuListener {
    void checkPipeAllignment();
    void changeBoardSize(int rows, int cols);
    void resetGame();
    void exitApp();
}

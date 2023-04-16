package sk.amjj.view.swingView;

import java.util.ArrayList;

import sk.amjj.controller.IEventListener;
import sk.amjj.universalStructs.Coords;

public class EventDispatcher {
    private ArrayList<IEventListener> listeners = new ArrayList<>();

    public void addListener(IEventListener listener) {
        this.listeners.add(listener);
    }

    public void rotatePipe(Coords pos, boolean clockwise) {
        for (IEventListener listener : listeners) {
            listener.rotatePipe(pos, clockwise);
        }
    }

    public void changeBoardSize(int rows, int cols) {
        for (IEventListener listener : listeners) {
            listener.changeBoardSize(rows, cols);
        }
    }

    public void resetGame() {
        for (IEventListener listener : listeners) {
            listener.resetGame();
        }
    }

    public void checkPipeAllignment() {
        for (IEventListener listener : listeners) {
            listener.checkPipeAllignment();
        }
    }

    public void exitApp() {
        for (IEventListener listener : listeners) {
            listener.exitApp();
        }
    }
}

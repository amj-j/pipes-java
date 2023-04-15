package sk.amjj.view.swingView.menu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import sk.amjj.controller.IEventListener;

public class MenuPanelListener extends KeyAdapter {
    private ArrayList<IEventListener> listeners = new ArrayList<>();

    public void addListener(IEventListener listener) {
        this.listeners.add(listener);
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

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
                resetGame();
                break;
            
            case KeyEvent.VK_ENTER:
                checkPipeAllignment();
                break;
            
            case KeyEvent.VK_ESCAPE:
                exitApp();
                break;
        
            default:
                return;
        }
    }

}

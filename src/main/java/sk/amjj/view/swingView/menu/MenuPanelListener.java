package sk.amjj.view.swingView.menu;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import sk.amjj.controller.interfaces.IMenuListener;

public class MenuPanelListener extends KeyAdapter {
    private ArrayList<IMenuListener> listeners = new ArrayList<>();

    public void addListener(IMenuListener listener) {
        this.listeners.add(listener);
    }

    public void changeBoardSize(int rows, int cols) {
        for (IMenuListener listener : listeners) {
            listener.changeBoardSize(rows, cols);
        }
    }

    public void resetGame() {
        for (IMenuListener listener : listeners) {
            listener.resetGame();
        }
    }

    public void checkPipeAllignment() {
        for (IMenuListener listener : listeners) {
            listener.resetGame();
        }
    }

    public void exitApp() {
        for (IMenuListener listener : listeners) {
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

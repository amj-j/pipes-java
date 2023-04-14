package sk.amjj.view.swingView.board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import sk.amjj.controller.IEventListener;
import sk.amjj.view.DefaultSettings;
import sk.amjj.view.swingView.board.tiles.PipeTile;

public class PipeTileListener extends MouseAdapter {
    private ArrayList<IEventListener> listeners = new ArrayList<>();

    public PipeTileListener() {
        super();
    }

    public void addListener(IEventListener listener) {
        this.listeners.add(listener);
    } 

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean clockwise;
        if (e.getButton() == MouseEvent.BUTTON1) {
            clockwise = false;
        }
        else if(e.getButton() == MouseEvent.BUTTON3) {
            clockwise = true;
        }
        else {
            return;
        }
        PipeTile tile = (PipeTile) e.getComponent();
        for (IEventListener listener : listeners) {
            listener.rotatePipe(tile.getPositon(), clockwise);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setBackground(DefaultSettings.HIGHLIGHT_MOUSE_HOVER_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setBackground(DefaultSettings.BOARD_COLOR);
    }


}

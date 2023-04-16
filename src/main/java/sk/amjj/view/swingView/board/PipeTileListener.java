package sk.amjj.view.swingView.board;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import lombok.Getter;
import lombok.Setter;
import sk.amjj.view.DefaultSettings;
import sk.amjj.view.swingView.EventDispatcher;
import sk.amjj.view.swingView.board.tiles.PipeTile;
import sk.amjj.view.swingView.board.tiles.Tile;

public class PipeTileListener extends MouseAdapter {
    private EventDispatcher eventDispatcher;

    @Getter @Setter
    private boolean correctnessHighlighted = false;

    public PipeTileListener(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
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
        eventDispatcher.rotatePipe(tile.getPositon(), clockwise);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setBackground(DefaultSettings.HIGHLIGHT_MOUSE_HOVER_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setBackground(((Tile) e.getComponent()).getBgColor());
    }


}

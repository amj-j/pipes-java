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
    private PipeTile pressedTile;

    @Getter @Setter
    private boolean correctnessHighlighted = false;

    public PipeTileListener(EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.pressedTile = (PipeTile) e.getComponent();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.pressedTile == e.getComponent() ) {
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
            eventDispatcher.rotatePipe(this.pressedTile.getPositon(), clockwise);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setBackground(DefaultSettings.HIGHLIGHT_MOUSE_HOVER_COLOR);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setBackground(((Tile) e.getComponent()).getBgColor());
        this.pressedTile = null;
    }


}

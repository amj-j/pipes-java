package sk.amjj.view.tiles;

import java.awt.Graphics;

import sk.amjj.universalEnums.Side;

public class PipeTile extends Tile {
    private final Side[] pipeEnds;

    public PipeTile(Side[] pipeEnds) {
        this.pipeEnds = pipeEnds;
    }

    @Override
    public void paint(Graphics g) {
        int pxWidth = super.getWidth() / 12;
        int pxHeight = super.getHeight() / 12;
        for (Side side : pipeEnds) {
            if (side == Side.UP) {
                g.fillRect(4*pxWidth, 0, 4*pxWidth, 8*pxHeight);
                g.fillRect(3*pxHeight, 0, 6*pxWidth, pxHeight);
            }
            else if (side == Side.RIGHT) {
                g.fillRect(4*pxWidth, 4*pxHeight, 8*pxWidth, 4*pxHeight);
                g.fillRect(11*pxHeight, 3*pxHeight, pxWidth, 6*pxHeight);
            }
            else if (side == Side.BOTTOM) {
                g.fillRect(4*pxWidth, 4*pxHeight, 4*pxWidth, 8*pxHeight);
                g.fillRect(3*pxHeight, 11*pxHeight, 6*pxWidth, pxHeight);
            }
            else if (side == Side.LEFT) {
                g.fillRect(0, 4*pxHeight, 8*pxWidth, 4*pxHeight);
                g.fillRect(0, 3*pxHeight, pxWidth, 6*pxHeight);
            }
        }
    }
}

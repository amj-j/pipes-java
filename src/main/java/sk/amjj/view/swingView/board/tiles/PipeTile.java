package sk.amjj.view.swingView.board.tiles;

import java.awt.Graphics;

import sk.amjj.universalEnums.Side;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;
import sk.amjj.view.DefaultSettings;

public class PipeTile extends Tile {
    private Side[] pipeEnds;

    public PipeTile(Coords position, PipeInfo pipeInfo) {
        super(position);
        this.pipeEnds = pipeInfo.getPipeEnds();
    }

    public void rotate(PipeInfo pipeInfo) {
        this.pipeEnds = pipeInfo.getPipeEnds();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(DefaultSettings.PIPE_COLOR);
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

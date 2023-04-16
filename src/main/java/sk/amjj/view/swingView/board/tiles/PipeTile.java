package sk.amjj.view.swingView.board.tiles;

import java.awt.Graphics;

import sk.amjj.universalEnums.Side;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;
import sk.amjj.view.DefaultSettings;

public class PipeTile extends Tile {
    private Side[] pipeEnds;

    private boolean isRouteEnd = false; 

    public PipeTile(Coords position, PipeInfo pipeInfo) {
        super(position);
        this.pipeEnds = pipeInfo.getPipeEnds();
    }

    public void setAsRouteEnd() {
        this.isRouteEnd = true;
    }

    public void rotate(PipeInfo pipeInfo) {
        this.pipeEnds = pipeInfo.getPipeEnds();
        repaint();
    }

    @Override
    public void paint(Graphics g) {   
        if (isRouteEnd) {
            g.setColor(DefaultSettings.ROUTE_END_COLOR);
            g.drawRect(0, 0, getWidth(), getHeight());
        }
        double pxWidth = ((double) super.getWidth()) / 12;
        double pxHeight = ((double) super.getHeight()) / 12;
        for (Side side : pipeEnds) {
            if (side == Side.UP) {
                fillRect(g, 4*pxWidth, 0.0, 4*pxWidth, 8*pxHeight);
                //drawRect(g, 4*pxWidth, 0.0, 4*pxWidth, 8*pxHeight);
                fillRect(g, 3*pxHeight, 0.0, 6*pxWidth, pxHeight);
                drawRect(g, 3*pxHeight, 0.0, 6*pxWidth, pxHeight);
            }
            else if (side == Side.RIGHT) {
                fillRect(g, 4*pxWidth, 4*pxHeight, 8*pxWidth, 4*pxHeight);
                //drawRect(g, 4*pxWidth, 4*pxHeight, 8*pxWidth, 4*pxHeight);
                fillRect(g, 11*pxHeight, 3*pxHeight, pxWidth, 6*pxHeight);
                drawRect(g, 11*pxHeight, 3*pxHeight, pxWidth, 6*pxHeight);
            }
            else if (side == Side.BOTTOM) {
                fillRect(g, 4*pxWidth, 4*pxHeight, 4*pxWidth, 8*pxHeight);
                //drawRect(g, 4*pxWidth, 4*pxHeight, 4*pxWidth, 8*pxHeight);
                fillRect(g, 3*pxHeight, 11*pxHeight, 6*pxWidth, pxHeight);
                drawRect(g, 3*pxHeight, 11*pxHeight, 6*pxWidth, pxHeight);
            }
            else if (side == Side.LEFT) {
                fillRect(g, 0.0, 4*pxHeight, 8*pxWidth, 4*pxHeight);
                //drawRect(g, 0.0, 4*pxHeight, 8*pxWidth, 4*pxHeight);
                fillRect(g, 0.0, 3*pxHeight, pxWidth, 6*pxHeight);
                drawRect(g, 0.0, 3*pxHeight, pxWidth, 6*pxHeight);
            }
        }
    }

    private void fillRect(Graphics g, double x, double y, double width, double height) {
        g.setColor(DefaultSettings.PIPE_COLOR);
        g.fillRect((int) Math.round(x), (int) Math.round(y), (int) Math.round(width), (int) Math.round(height));
    }

    private void drawRect(Graphics g, double x, double y, double width, double height) {
        g.setColor(DefaultSettings.PIPE_OUTLINE_COLOR);
        g.drawRect((int) Math.round(x), (int) Math.round(y), (int) Math.round(width), (int) Math.round(height));
    }
}

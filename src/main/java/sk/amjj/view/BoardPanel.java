package sk.amjj.view;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.view.tiles.FreeTile;
import sk.amjj.view.tiles.PipeTile;
import sk.amjj.view.tiles.Tile;

public class BoardPanel extends JPanel {
    Tile[][] tiles;
    JPanel board = new JPanel();

    public BoardPanel() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (board == null) {
                    return;
                }
                setLayoutParameters();
                repaint();
            }
        });
    }

    public void newBoard(BoardInfo boardInfo) throws InvalidBoardInfoException {
        board.removeAll();
        this.tiles = new Tile[boardInfo.getCols()][boardInfo.getRows()];
        setLayoutParameters();
        board.setLayout(new GridLayout(boardInfo.getRows(), boardInfo.getCols()));
        initTiles(boardInfo);
        setBackground(DefaultSettings.BG_COLOR); 
    }

    public void initTiles(BoardInfo boardInfo) throws InvalidBoardInfoException {
        for (int y = 0; y < tiles[0].length; y++) {
            for (int x = 0; x < tiles.length; x++) {
                try {
                    if (boardInfo.isAPipe(new Coords(x,y))) {
                        this.tiles[x][y] = new PipeTile(boardInfo.getPipeInfo(new Coords(x, y)).getPipeEnds());    
                    }
                    else {
                        this.tiles[x][y] = new FreeTile();
                    }
                }
                catch (NotAPipeException | CoordsOutOfRangeException e) {
                    throw new InvalidBoardInfoException();
                }
                board.add(this.tiles[x][y]);
            }
        }
    }

    private void setLayoutParameters() {
        double panelWHRatio = ((double) getWidth()) / ((double) getHeight());
        double boardCRRatio = ((double) tiles.length) / ((double) tiles[0].length);
        if (panelWHRatio > 1.0) {
            if (boardCRRatio > 1.0) {
                if (panelWHRatio < boardCRRatio) {
                    constraintByWidth();
                }
                else {
                    constraintByHeight();
                }
            }
            else {
                constraintByHeight();
            }
        }
        else {
            if (boardCRRatio < 1.0) {
                if (panelWHRatio > boardCRRatio) {
                    constraintByHeight();
                }
                else {
                    constraintByWidth();
                }
            }
            else {
                constraintByWidth();
            }
        } 
    }

    private void constraintByWidth() {
        int cols = tiles.length;
        int rows = tiles[0].length;
        int tileSize = getWidth() / (cols + 1);
        int boardWidth = cols*tileSize;
        int boardHeight = rows*tileSize;
        int offsetX = tileSize/2;
        int offsetY = (getHeight() - boardHeight)/2;
        this.board.setBounds(offsetX, offsetY, boardWidth, boardHeight);
    }

    private void constraintByHeight() {
        int cols = tiles.length;
        int rows = tiles[0].length;
        int tileSize = getWidth() / (rows + 1);
        int boardWidth = cols*tileSize;
        int boardHeight = rows*tileSize;
        int offsetX = (getWidth() - boardWidth)/2;
        int offsetY = tileSize/2;
        this.board.setBounds(offsetX, offsetY, boardWidth, boardHeight);
    }
}

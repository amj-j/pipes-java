package sk.amjj.view.swingView.board;

import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import sk.amjj.controller.interfaces.IBoardListener;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;
import sk.amjj.view.DefaultSettings;
import sk.amjj.view.interfaces.IBoardView;
import sk.amjj.view.swingView.board.tiles.FreeTile;
import sk.amjj.view.swingView.board.tiles.PipeTile;
import sk.amjj.view.swingView.board.tiles.Tile;

public class BoardPanel extends JPanel implements IBoardView {
    private Tile[][] tiles;
    private JPanel board = new JPanel();
    private PipeTileListener pipeTileListener = new PipeTileListener();
    boolean correctnessHighlighted = false;

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

    @Override
    public void addBoardListener(IBoardListener listener) {
        this.pipeTileListener.addListener(listener);
    }

    @Override
    public void setNewBoard(BoardInfo boardInfo) throws InvalidBoardInfoException {
        board.removeAll();
        this.tiles = new Tile[boardInfo.getCols()][boardInfo.getRows()];
        setLayoutParameters();
        board.setLayout(new GridLayout(boardInfo.getRows(), boardInfo.getCols()));
        initTiles(boardInfo);
        setBackground(DefaultSettings.BG_COLOR); 
    }

    private void initTiles(BoardInfo boardInfo) throws InvalidBoardInfoException {
        for (int y = 0; y < tiles[0].length; y++) {
            for (int x = 0; x < tiles.length; x++) {
                try {
                    Coords pos = new Coords(x, y);
                    if (boardInfo.isAPipe(new Coords(x,y))) {
                        this.tiles[x][y] = new PipeTile(pos, boardInfo.getPipeInfo(new Coords(x, y)));    
                    }
                    else {
                        this.tiles[x][y] = new FreeTile(pos);
                    }
                }
                catch (NotAPipeException | CoordsOutOfRangeException e) {
                    throw new InvalidBoardInfoException();
                }
                this.board.add(this.tiles[x][y]);
                this.tiles[x][y].addMouseListener(pipeTileListener);
            }
        }
    }

    @Override
    public void rotatePipe(PipeInfo pipeInfo, Coords position) throws CoordsOutOfRangeException, NotAPipeException {
        if (!position.isInRange(0, 0, tiles.length, tiles[0].length)) {
            throw new CoordsOutOfRangeException();
        }
        if (tiles[position.getX()][position.getY()] instanceof PipeTile) {
            PipeTile pipeTile = (PipeTile) tiles[position.getX()][position.getY()];
            pipeTile.rotate(pipeInfo);
        }
        else {
            throw new NotAPipeException();
        }
    }

    @Override
    public boolean isCorresctnessHighlighted() {
        return this.correctnessHighlighted;
    }

    @Override
    public void highlightCorrectness(AllignmentCorrectness allignmentCorrectness) {
        for (Coords tilePos : allignmentCorrectness.getCorrectlyAlligned()) {
            tiles[tilePos.getX()][tilePos.getY()].setBackground(DefaultSettings.HIGHLIGHT_CORRECT_COLOR);
        }
        Coords firstIncorrectPos = allignmentCorrectness.getFirstIncorrect();
        tiles[firstIncorrectPos.getX()][firstIncorrectPos.getY()].setBackground(DefaultSettings.HIGHLIGHT_INCORRECT_COLOR);
        this.correctnessHighlighted = true;
    }

    @Override
    public void dehighlightCorrectness() {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles.length; y++) {
                if (tiles[x][y] instanceof PipeTile) {
                    tiles[x][y].setBackground(DefaultSettings.BOARD_COLOR);
                }
            }
        }
        this.correctnessHighlighted = false;
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
package sk.amjj.view.swingView;

import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import sk.amjj.controller.IEventListener;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;
import sk.amjj.view.DefaultSettings;
import sk.amjj.view.IView;
import sk.amjj.view.swingView.board.BoardPanel;
import sk.amjj.view.swingView.board.IBoardPanel;
import sk.amjj.view.swingView.menu.IMenuPanel;
import sk.amjj.view.swingView.menu.MenuPanel;

public class MainWindow extends JFrame implements IView {
    IBoardPanel board;
    IMenuPanel menu;
    EventDispatcher eventDispatcher = new EventDispatcher();

    public MainWindow() {
        this.board = new BoardPanel(eventDispatcher);
        this.menu = new MenuPanel(eventDispatcher);

        setSize(new Dimension(DefaultSettings.MAIN_WINDOW_WIDTH, DefaultSettings.MAIN_WINDOW_HEIGHT));
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int windowWidth = getContentPane().getWidth();
                int windowHeight = getContentPane().getHeight();
                board.setPreferredSize(new Dimension(windowWidth, 5*(windowHeight/6)));
                menu.setPreferredSize(new Dimension(windowWidth, windowHeight/6));
            }
        });

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_R:
                                eventDispatcher.resetGame();
                                break;
                            
                        case KeyEvent.VK_ENTER:
                            eventDispatcher.checkPipeAllignment();
                            break;
                        
                        case KeyEvent.VK_ESCAPE:
                            eventDispatcher.exitApp();
                            break;
                    
                        default:
                    }
                }
                return false;
            }
        });

        
        this.board.addTo(this);
        this.menu.addTo(this);
    }

    @Override
    public void setNewBoard(BoardInfo boardInfo) throws InvalidBoardInfoException {
        board.setNewBoard(boardInfo);
    }

    @Override
    public void rotatePipe(PipeInfo pipeInfo, Coords position) throws CoordsOutOfRangeException, NotAPipeException {
        board.rotatePipe(pipeInfo, position);
    }

    @Override
    public boolean isCorresctnessHighlighted() {
        return board.isCorresctnessHighlighted();
    }

    @Override
    public void highlightCorrectness(AllignmentCorrectness allignmentCorrectness) {
        board.highlightCorrectness(allignmentCorrectness);
    }

    @Override
    public void dehighlightCorrectness() {
        board.dehighlightCorrectness();
    }

    @Override
    public void showWonGames(int gamesWon) {
        menu.showWonGames(gamesWon);
    }

    @Override
    public void showBoardSize(int rows, int cols) {
        menu.showBoardSize(rows, cols);
    }

    @Override
    public void addEventListener(IEventListener listener) {
        this.eventDispatcher.addListener(listener);
    }

    @Override
    public void setVisible() {
        super.setVisible(true);
    }
}

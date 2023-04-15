package sk.amjj.view.swingView.menu;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sk.amjj.controller.IEventListener;
import sk.amjj.view.DefaultSettings;

public class MenuPanel extends JPanel implements IMenuPanel {
    private MenuPanelListener menuPanelListener = new MenuPanelListener();
    private FlowLayout layout = new FlowLayout(FlowLayout.CENTER);

    JLabel gamesWon;
    JLabel sizeInfo;
    JSlider sizeSlider;
    JButton resetButton;
    JButton checkerButton;
    
    public MenuPanel() {
        setLayout(this.layout);
        setBackground(DefaultSettings.BG_COLOR);
        initGamesWon();
        initSizeInfo();
        initSizeSlider();
        initResetButton();
        initCheckerButton();

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int fontSize = getWidth() /32;
                Font font = new Font(DefaultSettings.FONT_NAME, Font.BOLD, fontSize);
                gamesWon.setForeground(DefaultSettings.FONT_COLOR);
                gamesWon.setFont(font);
                sizeInfo.setForeground(DefaultSettings.FONT_COLOR);
                sizeInfo.setFont(font);
                layout.setHgap(getWidth()/25);
            }
        });
    }

    private void initGamesWon() {
        this.gamesWon = new JLabel("Score: N/A");
        this.add(this.gamesWon);
    }

    private void initSizeInfo() {
        this.sizeInfo = new JLabel("Size: N/A");
        this.add(this.sizeInfo);
    }

    private void initSizeSlider() {
        this.sizeSlider = new JSlider(JSlider.HORIZONTAL, 8, 12, 8);
        sizeSlider.setBackground(DefaultSettings.BG_COLOR);
        sizeSlider.setMinorTickSpacing(2);
        sizeSlider.setMajorTickSpacing(2);
        sizeSlider.setSnapToTicks(true);
        sizeSlider.setPaintTicks(true);
        sizeSlider.setPaintLabels(true);
        this.sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                int value = source.getValue();
                menuPanelListener.changeBoardSize(value, value);
            }
        });
        this.add(this.sizeSlider);
    }

    private void initResetButton() {
        this.resetButton = new JButton("Reset");
        this.resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanelListener.resetGame();
            }
        });
        this.add(this.resetButton);
    }

    private void initCheckerButton() {
        this.checkerButton = new JButton("Check");
        this.checkerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPanelListener.checkPipeAllignment();
            }
        });
        this.add(this.checkerButton);
    }

    @Override
    public void addEventListener(IEventListener listener) {
        this.menuPanelListener.addListener(listener);
    }

    @Override
    public void showWonGames(int gamesWon) {
        this.gamesWon.setText("Score: " + gamesWon);
    }

    @Override
    public void showBoardSize(int rows, int cols) {
        this.sizeInfo.setText("Size: " + rows + "x" + cols);
    }

    @Override
    public void addTo(Container container) {
        container.add(this);
    }

    @Override
    public void setPreferredSize(Dimension d) {
        super.setPreferredSize(d);
    }
}

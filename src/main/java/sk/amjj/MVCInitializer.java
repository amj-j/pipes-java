package sk.amjj;

import sk.amjj.controller.BoardController;
import sk.amjj.controller.MenuController;
import sk.amjj.controller.interfaces.IBoardListener;
import sk.amjj.controller.interfaces.IMenuListener;
import sk.amjj.model.PipesModel;
import sk.amjj.model.interfaces.IPipesModel;
import sk.amjj.view.interfaces.IBoardView;
import sk.amjj.view.interfaces.IMenuView;
import sk.amjj.view.swingView.board.BoardPanel;
import sk.amjj.view.swingView.menu.MenuPanel;

public class MVCInitializer {
    private IPipesModel model;
    private IBoardView boardView;
    private IMenuView menuView;
    private IBoardListener boardListener;
    private IMenuListener menuListener;

    public MVCInitializer() {
        this.model = new PipesModel();
        this.boardView = new BoardPanel();
        this.menuView = new MenuPanel();
        this.boardListener = new BoardController(model, boardView);
        this.menuListener = new MenuController(model, boardView, menuView);

        this.boardView.addBoardListener(boardListener);
        this.menuView.addMenuListener(menuListener);
    }
}

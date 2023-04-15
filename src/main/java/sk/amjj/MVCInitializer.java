package sk.amjj;

import sk.amjj.controller.IEventListener;
import sk.amjj.controller.PipesController;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.model.IPipesModel;
import sk.amjj.model.PipesModel;
import sk.amjj.view.IView;
import sk.amjj.view.swingView.MainWindow;

public class MVCInitializer {
    private IPipesModel model;
    private IView view;
    private IEventListener eventListener;

    public MVCInitializer() {
        model = new PipesModel();
        view = new MainWindow();
        eventListener = new PipesController(model, view);

        view.addEventListener(eventListener);
        try {
            view.setNewBoard(model.getBoard());
        } catch (InvalidBoardInfoException e) {
            System.err.println("Bad response from the model.");
            e.print();
            e.printStackTrace();
            System.exit(0);
        }
        view.showBoardSize(model.getBoardRows(), model.getBoardCols());
        view.showWonGames(model.getWonGames());
        view.setVisible();
    }
}

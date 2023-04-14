package sk.amjj;

import sk.amjj.controller.IEventListener;
import sk.amjj.controller.PipesController;
import sk.amjj.model.IPipesModel;
import sk.amjj.model.PipesModel;
import sk.amjj.view.IView;
import sk.amjj.view.swingView.MainWindow;

public class MVCInitializer {
    private IPipesModel model;
    private IView view;
    private IEventListener eventListener;

    public MVCInitializer() {
        this.model = new PipesModel();
        this.view = new MainWindow();
        this.eventListener = new PipesController(model, view);

        this.view.addEventListener(eventListener);
    }
}

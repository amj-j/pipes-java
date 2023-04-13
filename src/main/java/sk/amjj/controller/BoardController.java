package sk.amjj.controller;

import lombok.AllArgsConstructor;
import sk.amjj.controller.interfaces.IBoardListener;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.exceptions.PipesException;
import sk.amjj.model.interfaces.IPipesModel;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;
import sk.amjj.view.interfaces.IBoardView;

@AllArgsConstructor
public class BoardController implements IBoardListener {
    private IPipesModel model;
    private IBoardView boardView;

    @Override
    public void rotatePipe(Coords pos, boolean clockwise) {
        try {
            PipeInfo pipeInfo = model.rotatePipe(pos, clockwise);
            try {
                if (boardView.isCorresctnessHighlighted()) {
                    boardView.dehighlightCorrectness();
                }
                boardView.rotatePipe(pipeInfo, pos);
            }
            catch (NotAPipeException e) {
                System.err.println("Bad response from the model.");
                failOnException(e);;
            }
            catch (CoordsOutOfRangeException e) {
                System.err.println("Bad response from the model.");
                failOnException(e);
            }
        } 
        catch (NotAPipeException e) {
            System.err.println("Bad request from the view.");
            failOnException(e);;
        }
        catch (CoordsOutOfRangeException e) {
            System.err.println("Bad request from the view.");
            failOnException(e);
        }
    }

    private void failOnException(PipesException e) {
        e.print();
        e.printStackTrace();
        System.exit(0);
    }
}

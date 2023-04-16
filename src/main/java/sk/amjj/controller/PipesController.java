package sk.amjj.controller;

import lombok.AllArgsConstructor;
import sk.amjj.exceptions.CoordsOutOfRangeException;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.exceptions.NotAPipeException;
import sk.amjj.exceptions.PipesException;
import sk.amjj.model.IPipesModel;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.universalStructs.Coords;
import sk.amjj.universalStructs.PipeInfo;
import sk.amjj.view.IView;

@AllArgsConstructor
public class PipesController implements IEventListener {
    private IPipesModel model;
    private IView view;

    @Override
    public void rotatePipe(Coords pos, boolean clockwise) {
        try {
            PipeInfo pipeInfo = model.rotatePipe(pos, clockwise);
            try {
                if (view.isCorresctnessHighlighted()) {
                    view.dehighlightCorrectness();
                }
                view.rotatePipe(pipeInfo, pos);
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
            failOnException(e);
        }
        catch (CoordsOutOfRangeException e) {
            System.err.println("Bad request from the view.");
            failOnException(e);
        }
    }

    @Override
    public void checkPipeAllignment() {
        if (model.arePipesAlligned()) {
            BoardInfo boardInfo = model.initBoard(model.getBoardRows(), model.getBoardCols());
            try {
                view.setNewBoard(boardInfo);
            } catch (InvalidBoardInfoException e) {
                System.err.println("Bad response from the model.");
                failOnException(e);
            }
            model.addWonGame();
            view.showWonGames(model.getWonGames());
        }
        else {
            AllignmentCorrectness allignmentCorrectness = model.getAllignmentCorrectness();
            view.highlightCorrectness(allignmentCorrectness);
        }
        
    }

    @Override
    public void changeBoardSize(int rows, int cols) {
        BoardInfo boardInfo = model.initBoard(rows, cols);
        try {
            view.setNewBoard(boardInfo);
        } catch (InvalidBoardInfoException e) {
            System.err.println("Bad response from the model.");
            failOnException(e);
        }
        view.showBoardSize(model.getBoardRows(), model.getBoardCols());
        model.resetWonGames();
        view.showWonGames(model.getWonGames());
    }

    @Override
    public void resetGame() {
        BoardInfo boardInfo = model.initBoard(model.getBoardRows(), model.getBoardCols());
        try {
            view.setNewBoard(boardInfo);
        } catch (InvalidBoardInfoException e) {
            System.err.println("Bad response from the model.");
            failOnException(e);
        }
        model.resetWonGames();
        view.showWonGames(model.getWonGames());
    }

    @Override
    public void exitApp() {
        System.exit(0);
    }

    private void failOnException(PipesException e) {
        e.print();
        e.printStackTrace();
        System.exit(0);
    }
}

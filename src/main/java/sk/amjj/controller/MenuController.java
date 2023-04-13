package sk.amjj.controller;

import lombok.AllArgsConstructor;
import sk.amjj.controller.interfaces.IMenuListener;
import sk.amjj.exceptions.InvalidBoardInfoException;
import sk.amjj.model.interfaces.IPipesModel;
import sk.amjj.universalStructs.AllignmentCorrectness;
import sk.amjj.universalStructs.BoardInfo;
import sk.amjj.view.interfaces.IBoardView;
import sk.amjj.view.interfaces.IMenuView;

@AllArgsConstructor
public class MenuController implements IMenuListener {
    private IPipesModel model;
    private IBoardView boardView;
    private IMenuView menuView;

    @Override
    public void checkPipeAllignment() {
        if (model.arePipesAlligned()) {
            BoardInfo boardInfo = model.initBoard(model.getBoardRows(), model.getBoardCols());
            try {
                boardView.setNewBoard(boardInfo);
            } catch (InvalidBoardInfoException e) {
                System.err.println("Bad response from the model.");
                e.print();
                e.printStackTrace();
                System.exit(0);
            }
            model.addWonGame();
            menuView.showWonGames(model.getWonGames());
        }
        else {
            AllignmentCorrectness allignmentCorrectness = model.getAllignmentCorrectness();
            boardView.highlightCorrectness(allignmentCorrectness);
        }
        
    }

    @Override
    public void changeBoardSize(int rows, int cols) {
        BoardInfo boardInfo = model.initBoard(rows, cols);
        try {
            boardView.setNewBoard(boardInfo);
        } catch (InvalidBoardInfoException e) {
            System.err.println("Bad response from the model.");
            e.print();
            e.printStackTrace();
            System.exit(0);
        }
        menuView.showBoardSize(model.getBoardRows(), model.getBoardCols());
    }

    @Override
    public void resetGame() {
        BoardInfo boardInfo = model.initBoard(model.getBoardRows(), model.getBoardCols());
        try {
            boardView.setNewBoard(boardInfo);
        } catch (InvalidBoardInfoException e) {
            System.err.println("Bad response from the model.");
            e.print();
            e.printStackTrace();
            System.exit(0);
        }
        model.resetWonGames();
        menuView.showWonGames(model.getWonGames());
    }

    @Override
    public void exitApp() {
        System.exit(0);
    }
}

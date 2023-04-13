package sk.amjj.exceptions;

public class InvalidBoardInfoException extends PipesException {

    @Override
    public void print() {
        System.err.println("Given BoardInfo is invalid!");
    }
}

package sk.amjj.exceptions;

public class CoordsOutOfRangeException extends PipesException {

    @Override
    public void print() {
        System.err.println("Given position is not within the board!");
    }
}

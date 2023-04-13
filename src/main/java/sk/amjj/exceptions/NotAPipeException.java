package sk.amjj.exceptions;

public class NotAPipeException extends PipesException {

    @Override
    public void print() {
        System.err.println("Given position does not contain a pipe!");
    }
}

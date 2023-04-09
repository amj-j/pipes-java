package sk.amjj.model.creators;

import sk.amjj.model.data.Board;

public interface ICreator {
    Board createBoard(int rows, int cols);
}

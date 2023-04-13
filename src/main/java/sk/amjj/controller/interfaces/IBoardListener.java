package sk.amjj.controller.interfaces;

import sk.amjj.universalStructs.Coords;

public interface IBoardListener {
    void rotatePipe(Coords pos, boolean clockwise);
}

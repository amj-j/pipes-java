package sk.amjj.model.data;

import java.util.ArrayList;

import sk.amjj.universalEnums.Side;
import sk.amjj.universalStructs.Coords;

public class Pipe {
    private final ArrayList<Side> pipeEnds  = new ArrayList<>();
    private final ArrayList<Side> correctPipeEnds  = new ArrayList<>();
    Coords pos = new Coords();
    
    public Pipe(Coords pos) {
        this.pos = pos;
    }

    public void setPos(Coords pos) {
        this.pos = pos;
    }

    public Coords getPos() {
        return this.pos;
    }

    public void addSide(Side side) {
        this.correctPipeEnds.add(side);
        this.pipeEnds.add(side);
    }

    public boolean hasSide(Side side) {
        return this.pipeEnds.contains(side);
    }

    public Side[] getSides() {
        Side[] array = new Side[this.pipeEnds.size()];
        return this.pipeEnds.toArray(array);
    }

    /**
     * 
     * @param clockwise - pass true for next element clockwise, false for counter clockwise
     */

    public void rotate(boolean clockwise) {
        for (int i = 0; i < pipeEnds.size(); i++) {
            this.pipeEnds.set(i, this.pipeEnds.get(i).next(clockwise));
        }
    }

    public boolean isCorrectlyRotated() {
        return (pipeEnds.size() == correctPipeEnds.size()) && pipeEnds.containsAll(correctPipeEnds) && correctPipeEnds.containsAll(pipeEnds);
    }
}

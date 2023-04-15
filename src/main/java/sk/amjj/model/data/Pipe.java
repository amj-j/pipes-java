package sk.amjj.model.data;

import java.util.ArrayList;

import sk.amjj.universalEnums.Side;
import sk.amjj.universalStructs.Coords;

public class Pipe {
    private ArrayList<Boolean> pipeEnds  = new ArrayList<>();
    private ArrayList<Boolean> correctPipeEnds  = new ArrayList<>();
    Coords pos = new Coords();
    
    public Pipe(Coords pos) {
        this.pos = pos;
        for (int i = 0; i < Side.values().length; i++) {
            this.correctPipeEnds.add(false);
            this.pipeEnds.add(false);
        }
    }

    public void setPos(Coords pos) {
        this.pos = pos;
    }

    public Coords getPos() {
        return this.pos;
    }

    public void addSide(Side side) {
        this.correctPipeEnds.set(side.getIndex(), true);
        this.pipeEnds.set(side.getIndex(), true);
    }

    public boolean hasSide(Side side) {
        return this.pipeEnds.get(side.getIndex());
    }

    public Side[] getSides() {
        ArrayList<Side> ends = new ArrayList<>();
        for (int i = 0; i < pipeEnds.size(); i++) {
            if (pipeEnds.get(i)) {
                ends.add(Side.values()[i]);
            }
        }
        return ends.toArray(new Side[0]);
    }

    /**
     * 
     * @param clockwise - pass true for next element clockwise, false for counter clockwise
     */

    public void rotate(boolean clockwise) {
        if (clockwise) {
            pipeEnds.add(0, pipeEnds.remove(pipeEnds.size()-1));
        }
        else {
            pipeEnds.add(pipeEnds.remove(0));
        }
    }

    public boolean isCorrectlyRotated() {
        for (int i = 0; i < pipeEnds.size(); i++) {
            if (pipeEnds.get(i) != correctPipeEnds.get(i)) {
                return false;
            }
        }
        return true;
    }
}

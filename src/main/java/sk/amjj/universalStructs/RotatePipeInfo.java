package sk.amjj.universalStructs;

import sk.amjj.universalEnums.Side;

public class RotatePipeInfo extends PipeInfo {
    Coords position;
    
    public RotatePipeInfo(Side[] sides, Coords pos) {
        super(sides);
        this.position = new Coords(pos);
    }

    public Coords getPosition() {
        return this.position;
    }
}

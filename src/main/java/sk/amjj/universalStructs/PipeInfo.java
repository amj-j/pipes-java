package sk.amjj.universalStructs;

import sk.amjj.universalEnums.Side;

public class PipeInfo {
    private final Side[] pipeEnds;

    public PipeInfo(Side[] sides) {
        pipeEnds = sides;
    }

    public Side[] getPipeEnds() {
        Side[] rtrn = new Side[pipeEnds.length];
        System.arraycopy(pipeEnds, 0, rtrn, 0, pipeEnds.length);
        return rtrn;
    }
}

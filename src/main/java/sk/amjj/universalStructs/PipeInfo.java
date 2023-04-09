package sk.amjj.universalStructs;

import lombok.Getter;
import sk.amjj.model.enums.Side;

@Getter
public class PipeInfo {
    private final boolean[] pipeEnds;

    public PipeInfo(Side[] sides) {
        pipeEnds = new boolean[sides.length];
        System.arraycopy(sides, 0, pipeEnds, 0, sides.length);
    }
}

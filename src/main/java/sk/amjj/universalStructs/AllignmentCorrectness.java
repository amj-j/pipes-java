package sk.amjj.universalStructs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AllignmentCorrectness {
    private final ArrayList<Coords> correctlyAlligned;
    private final Coords firstIncorrect;

    public AllignmentCorrectness(ArrayList<Coords> correctlyAlligned, Coords firstIncorrect) {
        this.correctlyAlligned = correctlyAlligned;
        this.firstIncorrect = firstIncorrect;
    }

    public int getCorrectlyAllignedNum() {
        return correctlyAlligned.size();
    }

    public List<Coords> getCorrectlyAlligned() {
        return Collections.unmodifiableList(correctlyAlligned);
    }

    public Coords getFirstIncorrect() {
        return new Coords(firstIncorrect);
    }
}

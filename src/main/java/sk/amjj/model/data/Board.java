package sk.amjj.model.data;

import java.util.ArrayList;

import lombok.Getter;
import sk.amjj.universalStructs.Coords;

@Getter
public class Board {
    private final int rows;
    private final int cols;
    private ArrayList<Pipe> route = new ArrayList<>();

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public Coords getRouteStart() {
        return route.get(0).getPos();
    }

    public Coords getRouteEnd() {
        return route.get(route.size()-1).getPos();
    }

}

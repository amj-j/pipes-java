package sk.amjj.model;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class BoardInfo {
    private final int rows;
    private final int cols;
    private ArrayList<Pipe> route = new ArrayList<>();

    public BoardInfo(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

}

package sk.amjj.model;

import lombok.Data;

@Data
public class Coords {
    private int x = 0;
    private int y = 0;

    public Coords() {}
    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

package sk.amjj.universalStructs;

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

    public Coords(Coords origin) {
        this.x = origin.getX();
        this.y = origin.getY();
    }

    public void moveX(int amount) {
        this.x = this.x + amount;
    }

    public void moveY(int amount) {
        this.y = this.y + amount;
    }
}

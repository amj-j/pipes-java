package sk.amjj.universalStructs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Coords {
    private int x = 0;
    private int y = 0;

    public Coords() {}

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

    public boolean isInRange(Coords lim1, Coords lim2) {
        return ((isCoordInRange(this.x, lim1.getX(), lim2.getX()) && isCoordInRange(this.y, lim1.getY(), lim2.getY())));      
    }

    private boolean isCoordInRange(int coord, int lim1, int lim2) {
        if (lim1 <= lim2) {
            if (coord < lim1 || coord > lim2) {
                return false;
            }
        }
        else {
            if (coord < lim2 || coord > lim1) {
                return false;
            }
        }
        return true;
    }
}

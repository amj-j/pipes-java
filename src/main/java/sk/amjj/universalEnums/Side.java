package sk.amjj.universalEnums;

public enum Side {
    UP,
    RIGHT,
    BOTTOM,
    LEFT;

    /**
     * Returns next side in clockwise or counter clockwise direction
     * 
     * @param clockwise - pass true for next element clockwise, false for counter clockwise
     * @return next 'Side' object
     */

    public Side next(boolean clockwise) {
        for (int i = 0; i < values().length; i++) {
            if (this == values()[i]) {
                if (clockwise) {
                    if (i == values().length - 1) {
                        return values()[0];
                    }
                    else {
                        return values()[i+1];
                    }
                }
                else {
                    if (i == 0) {
                        return values()[values().length-1];
                    }
                    else {
                        return values()[i-1];
                    }
                }
            }
        }
        return null;
    }

    public Side opposite() {
        return next(true).next(true);
    }
}

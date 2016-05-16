package uk.ac.brighton.uni.ch629.ecengine.types;

public enum Direction {
    UP(0, 1), DOWN(0, -1), LEFT(-1, 0), RIGHT(1, 0), NONE(0, 0);

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x, y;

    public Direction opposite() {
        switch (this) {
            case UP: return DOWN;
            case DOWN: return UP;
            case LEFT: return RIGHT;
            case RIGHT: return LEFT;
        }
        return null;
    }

    public static Direction fromVector(Vector2d vec) {
        return fromXY((int) Math.ceil(vec.x), (int) Math.ceil(vec.y));
    }

    public static Direction fromXY(int x, int y) {
    if(x == 0) {
        if(y == 1) return UP;
        else if(y == -1) return DOWN;
    }else if(x == 1) {
        if(y == 0) return RIGHT;
    }else if(x == -1) {
        if(y == 0) return LEFT;
    }
        return NONE;
    }

    public Vector2d toVector() {
        switch(this) {
            case UP: return new Vector2d(0, 1);
            case DOWN: return new Vector2d(0, -1);
            case RIGHT: return new Vector2d(1, 0);
            case LEFT: return new Vector2d(-1, 0);
        }
        return null;
    }

    public boolean isLeftRight() {
        return this == LEFT || this == RIGHT;
    }

    public boolean isTopBottom() {
        return this == UP || this == DOWN;
    }
}
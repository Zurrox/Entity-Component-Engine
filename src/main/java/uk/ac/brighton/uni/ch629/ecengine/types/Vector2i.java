package uk.ac.brighton.uni.ch629.ecengine.types;

public class Vector2i {
    public int x, y;

    public Vector2i(final int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2i() {
        this.x = 0;
        this.y = 0;
    }

    public void setPos(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public void move(final int x, final int y) {//TODO: Maybe use Translate instead.
        this.x += x;
        this.y += y;
    }

    public void move(final Vector2i pos) {
        this.x += pos.x;
        this.y += pos.y;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public double getDistance(final Vector2i other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    public static Vector2i zero() {
        return new Vector2i(0, 0);
    }
}
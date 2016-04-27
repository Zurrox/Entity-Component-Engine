package uk.ac.brighton.uni.ch629.ecengine.types;

public class Vector2d {
    public static final Vector2d zero = new Vector2d(0, 0);
    public double x, y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d() {
        this.x = 0;
        this.y = 0;
    }

    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPos(Vector2d pos) {
        this.x = pos.x;
        this.y = pos.y;
    }

    public void move(double x, double y) {//TODO: Maybe use Translate instead.
        this.x += x;
        this.y += y;
    }

    public void move(Vector2d pos) {
        this.x += pos.x;
        this.y += pos.y;
    }

    public Vector2d offset(double x, double y) {
        return new Vector2d(this.x + x, this.y + y);
    }

    public Vector2d offset(Vector2d off) {
        return offset(off.x, off.y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDistance(Vector2d other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }
}
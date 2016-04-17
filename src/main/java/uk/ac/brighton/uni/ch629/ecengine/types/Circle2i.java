package uk.ac.brighton.uni.ch629.ecengine.types;

public class Circle2i extends Vector2i {
    public int radius;

    public Circle2i(int x, int y, final int radius) {
        super(x, y);
        this.radius = radius;
    }
}